package allchive.server.domain.common.aop.distributedLock;

import static allchive.server.core.consts.AllchiveConst.*;

import allchive.server.core.error.exception.InvalidLockIdentifierException;
import allchive.server.infrastructure.redis.lock.LockManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@RequiredArgsConstructor
@Slf4j
public class DistributedLockAop {
    private final LockManager lockManager;

    @Around("@annotation(allchive.server.domain.common.aop.distributedLock.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);
        return lockManager.lock(joinPoint, getKey(joinPoint, distributedLock.identifier()));
    }

    private String getKey(ProceedingJoinPoint joinPoint, String[] identifiers) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] methodParameterNames = methodSignature.getParameterNames();
        return REDISSON_LOCK_PREFIX + createDynamicKey(methodParameterNames, joinPoint.getArgs(), identifiers);
    }

    private String createDynamicKey(String[] methodParameterNames, Object[] methodArgs, String[] identifiers) {
        List<String> resultList = new ArrayList<>();
        for (String identifier : identifiers) {
            int indexOfKey = Arrays.asList(methodParameterNames).indexOf(identifier);
            Object arg = methodArgs[indexOfKey];
            if (arg == null) {
                throw InvalidLockIdentifierException.EXCEPTION;
            }
            resultList.add(arg.toString());
        }
        log.info(String.join(":", resultList));
        return String.join(":", resultList);
    }
}
