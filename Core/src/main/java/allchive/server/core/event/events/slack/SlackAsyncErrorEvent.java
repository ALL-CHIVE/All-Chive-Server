package allchive.server.core.event.events.slack;


import lombok.Builder;
import lombok.Getter;

@Getter
public class SlackAsyncErrorEvent {
    private String name;
    private Throwable throwable;
    private Object[] params;

    @Builder
    private SlackAsyncErrorEvent(String name, Throwable throwable, Object[] params) {
        this.name = name;
        this.throwable = throwable;
        this.params = params;
    }

    public static SlackAsyncErrorEvent of(String name, Throwable throwable, Object[] params) {
        return SlackAsyncErrorEvent.builder()
                .name(name)
                .throwable(throwable)
                .params(params)
                .build();
    }
}
