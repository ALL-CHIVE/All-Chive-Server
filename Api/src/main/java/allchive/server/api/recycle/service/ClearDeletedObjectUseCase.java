package allchive.server.api.recycle.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.recycle.model.dto.request.ClearDeletedObjectRequest;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.service.ContentTagGroupDomainService;
import allchive.server.domain.domains.content.validator.ContentValidator;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import allchive.server.domain.domains.recycle.validator.RecycleValidator;
import allchive.server.domain.domains.report.service.ReportDomainService;
import allchive.server.domain.domains.user.service.ScrapDomainService;
import allchive.server.infrastructure.s3.service.S3DeleteObjectService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class ClearDeletedObjectUseCase {
    private final RecycleValidator recycleValidator;
    private final ArchivingValidator archivingValidator;
    private final ContentValidator contentValidator;
    private final ContentAdaptor contentAdaptor;
    private final ContentTagGroupDomainService contentTagGroupDomainService;
    private final ScrapDomainService scrapDomainService;
    private final ArchivingDomainService archivingDomainService;
    private final ContentDomainService contentDomainService;
    private final RecycleDomainService recycleDomainService;
    private final ReportDomainService reportDomainService;
    private final S3DeleteObjectService s3DeleteObjectService;
    private final ArchivingAdaptor archivingAdaptor;

    @Transactional
    public void execute(ClearDeletedObjectRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(userId, request);
        List<Content> contents = contentAdaptor.findAllByArchivingIds(request.getArchivingIds());
        List<Long> contentsId = getContentsId(contents, request);
        List<Archiving> archivings = archivingAdaptor.findAllByIdIn(request.getArchivingIds());
        scrapDomainService.deleteAllByArchivingIdIn(request.getArchivingIds());
        contentTagGroupDomainService.deleteByContentIn(contents);
        contentDomainService.deleteAllById(contentsId);
        recycleDomainService.deleteAllByUserIdAndArchivingIdInOrUserIdAndContentIdIn(
                request.getArchivingIds(), request.getContentIds(), userId);
        reportDomainService.deleteAllByArchivingIdInOrContentIdIn(
                request.getArchivingIds(), request.getContentIds());
        archivingDomainService.deleteAllById(request.getArchivingIds());
        deleteS3Object(contents, archivings);
    }

    private void deleteS3Object(List<Content> contents, List<Archiving> archivings) {
        List<String> imageKeys =
                archivings.stream()
                        .map(Archiving::getImageUrl)
                        .filter(url -> !url.isEmpty())
                        .filter(url -> !url.startsWith("http"))
                        .collect(Collectors.toList());
        imageKeys.addAll(
                contents.stream()
                        .filter(content -> content.getContentType().equals(ContentType.IMAGE))
                        .map(Content::getImageUrl)
                        .filter(url -> !url.isEmpty())
                        .filter(url -> !url.startsWith("http"))
                        .collect(Collectors.toList()));
        s3DeleteObjectService.deleteS3Object(imageKeys);
    }

    private List<Long> getContentsId(List<Content> contents, ClearDeletedObjectRequest request) {
        List<Long> contentsId = contents.stream().map(Content::getId).collect(Collectors.toList());
        if (!request.getContentIds().isEmpty()) {
            if (contentsId.isEmpty()) {
                contentsId = new ArrayList<>();
            }
            contentsId.addAll(request.getContentIds());
        }
        return contentsId;
    }

    private void validateExecution(Long userId, ClearDeletedObjectRequest request) {
        recycleValidator.validateExist(request.getArchivingIds(), request.getContentIds(), userId);
        archivingValidator.verifyUserInIdList(userId, request.getArchivingIds());
        contentValidator.verifyUserInIdList(userId, request.getContentIds());
    }
}
