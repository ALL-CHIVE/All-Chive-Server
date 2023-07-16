package allchive.server.domain.domains.report.exception;

import static allchive.server.core.consts.AllchiveConst.BAD_REQUEST;

import allchive.server.core.dto.ErrorReason;
import allchive.server.core.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportErrorCode implements BaseErrorCode {
    DUPLICATED_REPORT_CONTENT(BAD_REQUEST, "REPORT_400_1", "이미 신고한 컨텐츠입니다."),
    DUPLICATED_REPORT_ARCHIVING(BAD_REQUEST, "REPORT_400_1", "이미 신고한 아카이빙입니다."),
    ;
    private int status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status, code, reason);
    }
}
