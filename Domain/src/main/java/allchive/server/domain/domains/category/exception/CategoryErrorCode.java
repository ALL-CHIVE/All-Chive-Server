package allchive.server.domain.domains.category.exception;

import static allchive.server.core.consts.AllchiveConst.*;

import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryErrorCode implements BaseErrorCode {
    CATEGORY_NOT_FOUND(BAD_REQUEST, "CATEGORY_400_1", "카테고리 정보를 찾을 수 없습니다."),

    NO_AUTHORITY_UPDATE_CATEGORY(FORBIDDEN, "CATEGORY_403_1", "카테고리 수정 권한이 없습니다.");
    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
