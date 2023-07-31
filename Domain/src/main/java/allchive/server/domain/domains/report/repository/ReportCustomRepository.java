package allchive.server.domain.domains.report.repository;


import java.util.List;

public interface ReportCustomRepository {
    Boolean queryReportExistByUserIdAndContentId(Long userId, Long contentId);

    Boolean queryReportExistByUserIdAndArchivingId(Long userId, Long archivingId);

    void queryDeleteAllByArchivingIdInOrContentIdIn(List<Long> archivingIds, List<Long> contentIds);
}
