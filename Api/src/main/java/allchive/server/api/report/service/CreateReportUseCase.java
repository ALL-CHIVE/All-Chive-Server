package allchive.server.api.report.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.report.model.dto.request.CreateReportRequest;
import allchive.server.api.report.model.mapper.ReportMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.content.validator.ContentValidator;
import allchive.server.domain.domains.report.domain.Report;
import allchive.server.domain.domains.report.domain.enums.ReportObjectType;
import allchive.server.domain.domains.report.service.ReportDomainService;
import allchive.server.domain.domains.report.validator.ReportValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateReportUseCase {
    private final ReportValidator reportValidator;
    private final ContentValidator contentValidator;
    private final ArchivingValidator archivingValidator;
    private final ReportMapper reportMapper;
    private final ReportDomainService reportDomainService;

    @Transactional
    public void execute(CreateReportRequest request, ReportObjectType type) {
        Long userId = SecurityUtil.getCurrentUserId();
        reportValidator.validateNotDuplicateReport(userId, request.getId(), type);
        switch (type) {
            case CONTENT -> contentValidator.validateExistById(request.getId());
            case ARCHIVING -> archivingValidator.validateExistById(request.getId());
        }
        Report report = reportMapper.toEntity(request, type, userId);
        reportDomainService.save(report);
    }
}
