package allchive.server.domain.domains.user.exception;


import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ScrapErrorCode implements BaseErrorCode {
    ;
    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
