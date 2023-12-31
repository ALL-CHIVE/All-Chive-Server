package allchive.server.domain.domains.content.exception;

import static allchive.server.core.consts.AllchiveConst.FORBIDDEN;
import static allchive.server.core.consts.AllchiveConst.NOT_FOUND;

import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContentErrorCode implements BaseErrorCode {
    ALREADY_DELETED_CONTENT(NOT_FOUND, "CONTENT_400_1", "이미 삭제된 컨텐츠입니다."),
    NOT_PUBLIC_CONTENT(NOT_FOUND, "CONTENT_400_2", "공개되지않은 컨텐츠입니다."),
    CONTENT_NOT_FOUND(NOT_FOUND, "CONTENT_404_1", "카테고리를 찾을 수 없습니다."),

    NO_AUTHORITY_UPDATE_CONTENT(FORBIDDEN, "TAG_403_1", "컨텐츠 수정 권한이 없습니다."),

    TAG_NOT_FOUND(NOT_FOUND, "TAG_404_1", "태그를 찾을 수 없습니다."),

    NO_AUTHORITY_UPDATE_TAG(FORBIDDEN, "TAG_403_1", "태그 수정 권한이 없습니다.");

    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
