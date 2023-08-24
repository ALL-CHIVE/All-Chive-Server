package allchive.server.api.content.service;

import static allchive.server.core.consts.AllchiveConst.MINUS_ONE;
import static allchive.server.core.consts.AllchiveConst.PLUS_ONE;

import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.request.UpdateContentRequest;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.service.ContentTagGroupDomainService;
import allchive.server.domain.domains.content.service.TagDomainService;
import allchive.server.domain.domains.content.validator.ContentValidator;
import allchive.server.domain.domains.content.validator.TagValidator;
import allchive.server.infrastructure.s3.service.S3DeleteObjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateContentUseCase {
    private final ContentValidator contentValidator;
    private final TagValidator tagValidator;
    private final ContentAdaptor contentAdaptor;
    private final TagAdaptor tagAdaptor;
    private final ContentMapper contentMapper;
    private final ContentDomainService contentDomainService;
    private final ContentTagGroupDomainService contentTagGroupDomainService;
    private final ArchivingDomainService archivingDomainService;
    private final TagDomainService tagDomainService;
    private final S3DeleteObjectService s3DeleteObjectService;

    @Transactional
    public void execute(Long contentId, UpdateContentRequest request) {
        validateExecution(contentId, request);
        updateTagUsedAt(request.getTagIds());
        regenerateContentTagGroup(contentId, request.getTagIds());
        updateArchiving(contentId, request.getArchivingId(), request.getContentType());
        eliminateOldImage(contentId, request.getImgUrl());
        contentDomainService.update(
                contentId,
                request.getArchivingId(),
                request.getLink(),
                request.getMemo(),
                UrlUtil.convertUrlToKey(request.getImgUrl()),
                request.getTitle());
    }

    private void updateArchiving(Long contentId, Long newArchivingId, ContentType contentType) {
        Content content = contentAdaptor.findById(contentId);
        if (!content.getArchivingId().equals(newArchivingId)) {
            archivingDomainService.updateContentCnt(
                    content.getArchivingId(), content.getContentType(), MINUS_ONE);
            archivingDomainService.updateContentCnt(newArchivingId, contentType, PLUS_ONE);
        }
    }

    private void validateExecution(Long contentId, UpdateContentRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        contentValidator.verifyUser(contentId, userId);
        tagValidator.validateExistTagsAndUser(request.getTagIds(), userId);
    }

    private void regenerateContentTagGroup(Long contentId, List<Long> tagIds) {
        Content content = contentAdaptor.findById(contentId);
        contentTagGroupDomainService.deleteAllByContent(content);
        List<Tag> tags = tagAdaptor.queryTagByTagIdIn(tagIds);
        List<ContentTagGroup> contentTagGroupList =
                contentMapper.toContentTagGroupEntityList(content, tags);
        contentTagGroupDomainService.saveAll(contentTagGroupList);
    }

    private void updateTagUsedAt(List<Long> tagIds) {
        tagAdaptor.queryTagByTagIdIn(tagIds).forEach(tagDomainService::updateUsedAt);
    }

    private void eliminateOldImage(Long contentId, String newUrl) {
        Content content = contentAdaptor.findById(contentId);
        if (UrlUtil.validateS3Key(content.getImageUrl())
                && !content.getImageUrl().equals(UrlUtil.convertUrlToKey(newUrl))) {
            s3DeleteObjectService.deleteS3Object(List.of(content.getImageUrl()));
        }
    }
}
