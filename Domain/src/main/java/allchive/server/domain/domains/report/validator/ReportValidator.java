package allchive.server.domain.domains.report.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.report.adaptor.ReportAdaptor;
import allchive.server.domain.domains.report.domain.enums.ReportObjectType;
import allchive.server.domain.domains.report.exception.exceptions.DuplicatedReportArchiving;
import allchive.server.domain.domains.report.exception.exceptions.DuplicatedReportContent;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class ReportValidator {
    private final ReportAdaptor reportAdaptor;

    public void validateNotDuplicateReport(Long userId, Long objectId, ReportObjectType type) {
        Boolean duplicateStatus = Boolean.FALSE;
        switch (type) {
            case CONTENT -> duplicateStatus =
                    reportAdaptor.queryReportExistByUserIdAndContentId(userId, objectId);
            case ARCHIVING -> duplicateStatus =
                    reportAdaptor.queryReportExistByUserIdAndArchivingId(userId, objectId);
        }
        if (duplicateStatus) {
            switch (type) {
                case CONTENT -> throw DuplicatedReportContent.EXCEPTION;
                case ARCHIVING -> throw DuplicatedReportArchiving.EXCEPTION;
            }
        }
    }
}
