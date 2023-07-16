package allchive.server.domain.domains.report.repository;

public interface ReportCustomRepository {
    Boolean queryReportExistByUserIdAndContentId(Long userId, Long contentId);

    Boolean queryReportExistByUserIdAndArchivingId(Long userId, Long archivingId);
}
