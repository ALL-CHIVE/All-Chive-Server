package allchive.server.core.event.events.slack;


import lombok.Builder;
import lombok.Getter;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Getter
public class SlackErrorEvent {
    private Exception exception;
    private ContentCachingRequestWrapper cachedRequest;

    @Builder
    private SlackErrorEvent(Exception exception, ContentCachingRequestWrapper cachedRequest) {
        this.exception = exception;
        this.cachedRequest = cachedRequest;
    }

    public static SlackErrorEvent of(
            Exception exception, ContentCachingRequestWrapper cachedRequest) {
        return SlackErrorEvent.builder().exception(exception).cachedRequest(cachedRequest).build();
    }
}
