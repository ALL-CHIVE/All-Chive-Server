package allchive.server.infrastructure.redis.lock;


import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public interface LockManager {
    public Object lock(ProceedingJoinPoint joinPoint, String key) throws Throwable;
}
