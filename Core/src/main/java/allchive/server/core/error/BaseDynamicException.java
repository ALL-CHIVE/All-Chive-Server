package allchive.server.core.error;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BaseDynamicException extends RuntimeException {
    private final int status;
    private final String code;
    private final String message;
}
