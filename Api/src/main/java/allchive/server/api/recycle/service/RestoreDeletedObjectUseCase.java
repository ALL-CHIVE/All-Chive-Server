package allchive.server.api.recycle.service;

import static allchive.server.core.consts.AllchiveConst.PLUS_ONE;

import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.recycle.model.dto.request.RestoreDeletedObjectRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.common.aop.distributedLock.DistributedLock;
import allchive.server.domain.common.enums.DistributedLockType;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.validator.ContentValidator;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import allchive.server.domain.domains.recycle.validator.RecycleValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class RestoreDeletedObjectUseCase {
    private final RecycleValidator recycleValidator;
    private final ArchivingValidator archivingValidator;
    private final ContentValidator contentValidator;
    private final ArchivingDomainService archivingDomainService;
    private final ContentDomainService contentDomainService;
    private final RecycleDomainService recycleDomainService;
    private final ContentAdaptor contentAdaptor;

    @Transactional
    @DistributedLock(lockType = DistributedLockType.RECYCLE, identifier ={"userId"})
    public void execute(RestoreDeletedObjectRequest request, Long userId) {
        validateExecution(request, userId);
        archivingDomainService.restoreByIdIn(request.getArchivingIds());
        contentDomainService.restoreByIdIn(request.getContentIds());
        increaseArchivingCount(request.getContentIds());
        recycleDomainService.deleteAllByUserIdAndArchivingIdInOrUserIdAndContentIdIn(
                request.getArchivingIds(), request.getContentIds(), userId);
    }

    private void increaseArchivingCount(List<Long> contentIds) {
        contentAdaptor
                .findAllByIdIn(contentIds)
                .forEach(
                        content ->
                                archivingDomainService.updateContentCnt(
                                        content.getArchivingId(),
                                        content.getContentType(),
                                        PLUS_ONE));
    }

    private void validateExecution(RestoreDeletedObjectRequest request, Long userId) {
        recycleValidator.validateExist(request.getArchivingIds(), request.getContentIds(), userId);
        archivingValidator.validateExistInIdList(request.getArchivingIds());
        contentValidator.validateExistInIdList(request.getContentIds());
    }
}
