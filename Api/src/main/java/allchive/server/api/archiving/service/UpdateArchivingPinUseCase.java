package allchive.server.api.archiving.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateArchivingPinUseCase {
    private final ArchivingValidator archivingValidator;
    private final ArchivingDomainService archivingDomainService;

    @Transactional
    public void execute(Long archivingId, Boolean cancel) {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(archivingId, userId, cancel);
        if (cancel) {
            archivingDomainService.updatePin(archivingId, userId, false);
        } else {
            archivingDomainService.updatePin(archivingId, userId, true);
        }
    }

    private void validateExecution(Long archivingId, Long userId, Boolean cancel) {
        archivingValidator.validateExistById(archivingId);
        archivingValidator.validateDeleteStatus(archivingId, userId);
        if (cancel) {
            archivingValidator.validateNotPinStatus(archivingId, userId);
        } else {
            archivingValidator.validateAlreadyPinStatus(archivingId, userId);
        }
    }
}
