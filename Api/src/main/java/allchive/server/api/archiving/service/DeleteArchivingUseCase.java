package allchive.server.api.archiving.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.recycle.model.mapper.RecycleMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.common.aop.distributedLock.DistributedLock;
import allchive.server.domain.common.enums.DistributedLockType;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteArchivingUseCase {
    private final ArchivingDomainService archivingDomainService;
    private final ArchivingValidator archivingValidator;
    private final RecycleMapper recycleMapper;
    private final RecycleDomainService recycleDomainService;

    @DistributedLock(
            lockType = DistributedLockType.ARCHIVING,
            identifier = {"archivingId"})
    public void execute(Long archivingId) {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(archivingId, userId);
        archivingDomainService.softDeleteById(archivingId);
        Recycle recycle = recycleMapper.toArchivingRecycleEntity(userId, archivingId);
        recycleDomainService.save(recycle);
    }

    private void validateExecution(Long archivingId, Long userId) {
        archivingValidator.verifyUser(userId, archivingId);
        archivingValidator.validateNotDeleted(archivingId);
    }
}
