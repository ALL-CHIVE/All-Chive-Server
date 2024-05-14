package allchive.server.infrastructure.redis.lock;

import static allchive.server.core.consts.AllchiveConst.LOCK_LEASE_TIME;
import static allchive.server.core.consts.AllchiveConst.LOCK_WAIT_TIME;
import static org.springframework.transaction.TransactionDefinition.PROPAGATION_NEVER;
import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRED;

import allchive.server.core.error.exception.AlreadyRedissonUnlockException;
import allchive.server.core.error.exception.InterruptRedissonException;
import allchive.server.core.error.exception.TxTemplateExecutionFailException;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class LockManagerImpl implements LockManager {
    private final RedissonClient redissonClient;
    private final TransactionTemplate txTemplate;

    public Object lock(ProceedingJoinPoint joinPoint, String key) throws Throwable {
        RLock rLock = redissonClient.getLock(key);
        log.info("lock : {}", key);
        try {
            boolean available = rLock.tryLock(LOCK_WAIT_TIME, LOCK_LEASE_TIME, TimeUnit.SECONDS);
            if (!available) {
                return false;
            }

            TransactionCallback txCallback =
                    status -> {
                        try {
                            return joinPoint.proceed();
                        } catch (Throwable e) {
                            throw TxTemplateExecutionFailException.EXCEPTION;
                        }
                    };
            return executeTransaction(
                    status -> executeTransaction(txCallback, PROPAGATION_REQUIRED),
                    PROPAGATION_NEVER);
        } catch (InterruptedException e) {
            throw InterruptRedissonException.EXCEPTION;
        } finally {
            try {
                log.info("unlock : {}", key);
                rLock.unlock();
            } catch (IllegalMonitorStateException e) {
                throw AlreadyRedissonUnlockException.EXCEPTION;
            }
        }
    }

    private <T> Object executeTransaction(TransactionCallback<T> block, int propagationBehavior) {
        txTemplate.setPropagationBehavior(propagationBehavior);
        return txTemplate.execute(block);
    }
}
