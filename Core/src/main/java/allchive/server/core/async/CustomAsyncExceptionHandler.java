package allchive.server.core.async;


import allchive.server.core.event.Event;
import allchive.server.core.event.events.slack.SlackAsyncErrorEvent;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... params) {
        log.error("Exception message - " + throwable);
        log.error("Method name - " + method.getName());
        for (Object param : params) {
            log.error("Parameter value - " + param);
        }
        Event.raise(SlackAsyncErrorEvent.of(method.getName(), throwable, params));
    }
}
