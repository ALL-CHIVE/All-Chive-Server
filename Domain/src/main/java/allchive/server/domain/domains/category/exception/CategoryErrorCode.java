package allchive.server.domain.domains.category.exception;


import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import static allchive.server.core.consts.AllchiveConst.BAD_REQUEST;
import static allchive.server.core.consts.AllchiveConst.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum CategoryErrorCode implements BaseErrorCode {
    CATEGORY_NOT_FOUND(BAD_REQUEST, "CATEGORY_400_1", "카테고리 정보를 찾을 수 없습니다.");
    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
