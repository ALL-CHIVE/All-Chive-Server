package allchive.server.api.recycle.service;

import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.recycle.model.dto.request.RestoreObjectRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.validator.ContentValidator;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import allchive.server.domain.domains.recycle.validator.RecycleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class RestoreObjectUseCase {
    private final RecycleValidator recycleValidator;
    private final ArchivingValidator archivingValidator;
    private final ContentValidator contentValidator;
    private final ArchivingDomainService archivingDomainService;
    private final ContentDomainService contentDomainService;
    private final RecycleDomainService recycleDomainService;

    @Transactional
    public void execute(RestoreObjectRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        recycleValidator.validateExist(request.getArchivingIds(), request.getContentIds(), userId);
        archivingValidator.validateExistInIdList(request.getArchivingIds());
        contentValidator.validateExistInIdList(request.getContentIds());
        archivingDomainService.restoreInIdList(request.getArchivingIds());
        contentDomainService.restoreInIdList(request.getContentIds());
        recycleDomainService
                .deleteAllByUserIdAndArchivingIdOrUserIdAndContentId(request.getArchivingIds(), request.getContentIds(), userId);
    }
}
