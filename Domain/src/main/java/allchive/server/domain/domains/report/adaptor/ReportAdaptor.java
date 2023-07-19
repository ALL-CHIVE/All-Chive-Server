package allchive.server.domain.domains.report.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.report.domain.Report;
import allchive.server.domain.domains.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;

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
}
