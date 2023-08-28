package allchive.server.core.event.events.slack;


import lombok.Builder;
import lombok.Getter;

@Getter
public class SlackErrorEvent {
    private Exception exception;

    @Builder
    private SlackErrorEvent(Exception exception) {
        this.exception = exception;
    }

    public static SlackErrorEvent from(Exception exception) {
        return SlackErrorEvent.builder().exception(exception).build();
    }
}
