package allchive.server.domain.domains.archiving.exception;

import static allchive.server.core.consts.AllchiveConst.*;

import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArchivingErrorCode implements BaseErrorCode {
    NOT_PUBLIC_ARCHIVING(BAD_REQUEST, "ARCHIVING_400_1", "공개된 아카이빙이 아닙니다."),
    DELETED_ARCHIVING(BAD_REQUEST, "ARCHIVING_400_2", "삭제된 아카이빙입니다."),
    ALREADY_PINNED_ARCHIVING(BAD_REQUEST, "ARCHIVING_400_3", "이미 고정 아카이빙입니다."),
    NOT_PINNED_ARCHIVING(BAD_REQUEST, "ARCHIVING_400_4", "고정되지않은 아카이빙입니다."),

    NO_AUTHORITY_UPDATE_ARCHIVING(FORBIDDEN, "ARCHIVING_403_1", "아카이빙 수정 권한이 없습니다."),

    ARCHIVING_NOT_FOUND(NOT_FOUND, "ARCHIVING_404_1", "아카이빙을 찾을 수 없습니다.");
    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
