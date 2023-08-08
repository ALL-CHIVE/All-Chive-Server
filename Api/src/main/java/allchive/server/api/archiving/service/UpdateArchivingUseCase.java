package allchive.server.api.archiving.service;


import allchive.server.api.archiving.model.dto.request.UpdateArchivingRequest;
import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.common.aop.distributedLock.DistributedLock;
import allchive.server.domain.common.enums.DistributedLockType;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateArchivingUseCase {
    private final ArchivingDomainService archivingDomainService;
    private final ArchivingAdaptor archivingAdaptor;
    private final ArchivingValidator archivingValidator;

    @Transactional
    @DistributedLock(lockType = DistributedLockType.ARCHIVING, identifier ={"archivingId"})
    public void execute(Long archivingId, UpdateArchivingRequest request) {
        validateExecution(archivingId);
        Archiving archiving = archivingAdaptor.findById(archivingId);
        archivingDomainService.updateArchiving(
                archiving,
                request.getTitle(),
                UrlUtil.convertUrlToKey(request.getImageUrl()),
                request.isPublicStatus(),
                request.getCategory());
    }

    private void validateExecution(Long archivingId) {
        Long userId = SecurityUtil.getCurrentUserId();
        archivingValidator.verifyUser(userId, archivingId);
    }
}
