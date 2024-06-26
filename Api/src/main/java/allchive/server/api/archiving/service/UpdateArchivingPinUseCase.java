package allchive.server.api.archiving.service;


import allchive.server.core.annotation.UseCase;
import allchive.server.domain.common.aop.distributedLock.DistributedLock;
import allchive.server.domain.common.enums.DistributedLockType;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateArchivingPinUseCase {
    private final ArchivingValidator archivingValidator;
    private final ArchivingDomainService archivingDomainService;

    @DistributedLock(
            lockType = DistributedLockType.ARCHIVING_PIN,
            identifier = {"archivingId", "userId"})
    public void execute(Long archivingId, Boolean cancel, Long userId) {
        validateExecution(archivingId, userId, cancel);
        archivingDomainService.updatePin(archivingId, userId, !cancel);
    }

    private void validateExecution(Long archivingId, Long userId, Boolean cancel) {
        archivingValidator.validateNotDeleteExceptUser(archivingId, userId);
        if (cancel) {
            archivingValidator.validateNotPinStatus(archivingId, userId);
        } else {
            archivingValidator.validateAlreadyPinStatus(archivingId, userId);
        }
    }
}
