package allchive.server.domain.domains.report.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.report.domain.Report;
import allchive.server.domain.domains.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ReportAdaptor {
    private final ReportRepository reportRepository;

    public void save(Report report) {
        reportRepository.save(report);
    }

    public Boolean queryReportExistByUserIdAndContentId(Long userId, Long contentId) {
        return reportRepository.queryReportExistByUserIdAndContentId(userId, contentId);
    }

    public Boolean queryReportExistByUserIdAndArchivingId(Long userId, Long archivingId) {
        return reportRepository.queryReportExistByUserIdAndArchivingId(userId, archivingId);
    }

    public void deleteAllByReportedUserId(Long userId) {
        reportRepository.deleteAllByReportedUserId(userId);
    }

    public void deleteAllByArchivingIdInOrContentIdIn(List<Long> archivingIds, List<Long> contentIds) {
        reportRepository.queryDeleteAllByArchivingIdInOrContentIdIn(archivingIds, contentIds);
    }
}
