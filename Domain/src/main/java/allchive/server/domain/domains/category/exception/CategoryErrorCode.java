package allchive.server.domain.domains.category.exception;

import static allchive.server.core.consts.AllchiveConst.*;

import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryErrorCode implements BaseErrorCode {
    NOT_PUBLIC_CATEGORY(BAD_REQUEST, "CATEGORY_400_1", "공개된 카테고리가 아닙니다."),
    DELETED_CATEGORY(BAD_REQUEST, "CATEGORY_400_2", "삭제된 카테고리입니다."),
    ALREADY_PINNED_CATEGORY(BAD_REQUEST, "CATEGORY_400_3", "이미 고정 카테고리입니다."),
    NOT_PINNED_CATEGORY(BAD_REQUEST, "CATEGORY_400_4", "고정되지않은 카테고리입니다."),

    NO_AUTHORITY_UPDATE_CATEGORY(FORBIDDEN, "CATEGORY_403_1", "카테고리 수정 권한이 없습니다."),

    CATEGORY_NOT_FOUND(NOT_FOUND, "CATEGORY_404_1", "카테고리를 찾을 수 없습니다.");
    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
