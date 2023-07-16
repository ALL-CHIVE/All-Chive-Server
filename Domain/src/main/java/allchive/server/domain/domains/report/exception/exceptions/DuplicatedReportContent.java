package allchive.server.domain.domains.report.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.report.exception.ReportErrorCode;

public class DuplicatedReportContent extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new DuplicatedReportContent();

    private DuplicatedReportContent() {
        super(ReportErrorCode.DUPLICATED_REPORT_CONTENT);
    }
}
