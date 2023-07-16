package allchive.server.domain.domains.report.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.report.exception.ReportErrorCode;

public class DuplicatedReportArchiving extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new DuplicatedReportArchiving();

    private DuplicatedReportArchiving() {
        super(ReportErrorCode.DUPLICATED_REPORT_ARCHIVING);
    }
}
