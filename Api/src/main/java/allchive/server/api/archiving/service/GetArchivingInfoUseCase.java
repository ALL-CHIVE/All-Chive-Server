package allchive.server.api.archiving.service;


import allchive.server.api.archiving.model.dto.response.ArchivingResponse;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetArchivingInfoUseCase {
    private final ArchivingValidator archivingValidator;
    private final ArchivingAdaptor archivingAdaptor;

    @Transactional(readOnly = true)
    public ArchivingResponse execute(Long archivingId) {
        validateExecution(archivingId);
        Archiving archiving = archivingAdaptor.findById(archivingId);
        return ArchivingResponse.of(archiving, Boolean.FALSE);
    }

    private void validateExecution(Long archivingId) {
        archivingValidator.validateExistById(archivingId);
    }
}
