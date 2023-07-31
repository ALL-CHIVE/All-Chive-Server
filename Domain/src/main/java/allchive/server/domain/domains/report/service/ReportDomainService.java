package allchive.server.domain.domains.report.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.report.adaptor.ReportAdaptor;
import allchive.server.domain.domains.report.domain.Report;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class ReportDomainService {
    private final ReportAdaptor reportAdaptor;

    public void save(Report report) {
        reportAdaptor.save(report);
    }

    public void deleteAllByReportedUserId(Long userId) {
        reportAdaptor.deleteAllByReportedUserId(userId);
    }

    public void deleteAllByArchivingIdInOrContentIdIn(List<Long> archivingIds, List<Long> contentIds) {
        reportAdaptor.deleteAllByArchivingIdInOrContentIdIn(archivingIds, contentIds);
    }
}
