package allchive.server.infrastructure.slack;


import allchive.server.core.helper.SpringEnvironmentHelper;
import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SlackHelper {
    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Value("${slack.webhook.slackUrl}")
    String slackUrl;

    public void sendErrorNotification(Payload payload) {
        final Slack slack = Slack.getInstance();

        try {
//            if (springEnvironmentHelper.isProdProfile()) {
                slack.send(slackUrl, payload);
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
