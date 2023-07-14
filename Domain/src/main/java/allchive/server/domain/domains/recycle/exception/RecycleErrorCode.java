package allchive.server.domain.domains.recycle.exception;

import static allchive.server.core.consts.AllchiveConst.NOT_FOUND;

import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecycleErrorCode implements BaseErrorCode {
    RECYCLE_CONTENT_NOT_FOUND(NOT_FOUND, "RECYCLE_404_1", "삭제된 컨텐츠를 찾을 수 없습니다."),
    RECYCLE_ARCHIVING_NOT_FOUND(NOT_FOUND, "RECYCLE_404_2", "삭제된 아카이빙을 찾을 수 없습니다."),
    ;
    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
