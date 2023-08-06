package allchive.server.infrastructure.redis.lock;

import static allchive.server.core.consts.AllchiveConst.LOCK_LEASE_TIME;
import static allchive.server.core.consts.AllchiveConst.LOCK_WAIT_TIME;

import allchive.server.core.error.exception.AlreadyRedissonUnlockException;
import allchive.server.core.error.exception.InterruptRedissonException;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class LockManagerImpl implements LockManager {
    private final RedissonClient redissonClient;

    @Transactional(propagation = Propagation.NEVER)
    public Object lock(ProceedingJoinPoint joinPoint, String key) throws Throwable {
        log.info("lock key : {}", key);
        RLock rLock = redissonClient.getLock(key);
        try {
            boolean available = rLock.tryLock(LOCK_WAIT_TIME, LOCK_LEASE_TIME, TimeUnit.SECONDS);
            if (!available) {
                return false;
            }
            return joinPoint.proceed();
        } catch (InterruptedException e) {
            throw InterruptRedissonException.EXCEPTION;
        } finally {
            try {
                rLock.unlock();
            } catch (IllegalMonitorStateException e) {
                throw AlreadyRedissonUnlockException.EXCEPTION;
            }
        }
    }
}
