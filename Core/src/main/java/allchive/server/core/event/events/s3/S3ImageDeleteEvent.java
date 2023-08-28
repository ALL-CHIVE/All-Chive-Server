package allchive.server.core.event.events.s3;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class S3ImageDeleteEvent {
    private final List<String> keys;

    @Builder
    private S3ImageDeleteEvent(List<String> keys) {
        this.keys = keys;
    }

    public static S3ImageDeleteEvent from(List<String> keys) {
        return S3ImageDeleteEvent.builder()
                .keys(keys)
                .build();
    }
}
