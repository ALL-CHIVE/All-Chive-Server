package allchive.server.api.common.aop;


import allchive.server.core.helper.SpringEnvironmentHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class TestTimerAop {
    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object handleEvent(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!springEnvironmentHelper.isProdAndDevProfile()) {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            Object result = joinPoint.proceed();

            stopWatch.stop();
            log.info(String.valueOf(stopWatch.getLastTaskTimeMillis()));
            return result;
        } else {
            return joinPoint.proceed();
        }
    }
}
