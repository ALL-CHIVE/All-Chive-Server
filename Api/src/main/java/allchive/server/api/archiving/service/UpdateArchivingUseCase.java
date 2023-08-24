package allchive.server.api.archiving.service;


import allchive.server.api.archiving.model.dto.request.UpdateArchivingRequest;
import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.infrastructure.s3.service.S3DeleteObjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class UpdateArchivingUseCase {
    private final ArchivingDomainService archivingDomainService;
    private final ArchivingAdaptor archivingAdaptor;
    private final ArchivingValidator archivingValidator;
    private final S3DeleteObjectService s3DeleteObjectService;

    @Transactional
    public void execute(Long archivingId, UpdateArchivingRequest request) {
        validateExecution(archivingId);
        Archiving archiving = archivingAdaptor.findById(archivingId);
        eliminateOldImage(archivingId, request.getImageUrl());
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

    private void eliminateOldImage(Long archivingId, String newUrl) {
        Archiving archiving = archivingAdaptor.findById(archivingId);
        if (UrlUtil.validateS3Key(archiving.getImageUrl())
                && !archiving.getImageUrl().equals(UrlUtil.convertUrlToKey(newUrl))) {
            s3DeleteObjectService.deleteS3Object(List.of(archiving.getImageUrl()));
        }
    }
}
