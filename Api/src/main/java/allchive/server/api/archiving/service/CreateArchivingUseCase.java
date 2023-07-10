package allchive.server.api.archiving.service;


import allchive.server.api.archiving.model.dto.request.CreateArchivingRequest;
import allchive.server.api.archiving.model.mapper.ArchivingMapper;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateArchivingUseCase {
    private final ArchivingMapper archivingMapper;
    private final ArchivingDomainService archivingDomainService;

    @Transactional
    public void execute(CreateArchivingRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        final Archiving archiving = archivingMapper.toEntity(request, userId);
        archivingDomainService.createArchiving(archiving);
    }
}
