package allchive.server.infrastructure.slack;


import allchive.server.infrastructure.slack.event.SlackAsyncErrorEvent;
import allchive.server.infrastructure.slack.event.SlackErrorEvent;
import com.slack.api.webhook.Payload;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SlackSendMessageHandler {
    private final SlackMessageGenerater slackMessageGenerater;
    private final SlackHelper slackHelper;

    @Async(value = "slackTaskExecutor")
    @EventListener(SlackErrorEvent.class)
    public void HandleError(SlackErrorEvent event) throws IOException {
        Payload payload = slackMessageGenerater.generateErrorMsg(event);
        slackHelper.sendErrorNotification(payload);
    }

    @Async(value = "slackTaskExecutor")
    @EventListener(SlackAsyncErrorEvent.class)
    public void HandleAsyncError(SlackAsyncErrorEvent event) throws IOException {
        Payload payload = slackMessageGenerater.generateAsyncErrorMsg(event);
        slackHelper.sendErrorNotification(payload);
    }
}
