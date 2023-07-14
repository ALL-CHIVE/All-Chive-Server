package allchive.server.api.archiving.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.recycle.model.mapper.RecycleMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.domain.enums.RecycleType;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteArchivingUseCase {
    private final ArchivingDomainService archivingDomainService;
    private final ArchivingValidator archivingValidator;
    private final RecycleMapper recycleMapper;
    private final RecycleDomainService recycleDomainService;

    @Transactional
    public void execute(Long archivingId) {
        Long userId = SecurityUtil.getCurrentUserId();
        archivingValidator.verifyUser(userId, archivingId);
        archivingDomainService.softDeleteById(archivingId);
        Recycle recycle =
                recycleMapper.toArchivingRecycleEntity(userId, archivingId, RecycleType.ARCHIVING);
        recycleDomainService.save(recycle);
    }
}
