package allchive.server.domain.domains.content.exception;

import static allchive.server.core.consts.AllchiveConst.NOT_FOUND;

import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContentErrorCode implements BaseErrorCode {
    CONTENT_NOT_FOUND(NOT_FOUND, "CATEGORY_404_1", "카테고리를 찾을 수 없습니다.");

    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
