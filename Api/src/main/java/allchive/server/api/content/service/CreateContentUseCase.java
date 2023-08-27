package allchive.server.api.content.service;

import static allchive.server.core.consts.AllchiveConst.PLUS_ONE;

import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.request.CreateContentRequest;
import allchive.server.api.content.model.dto.response.ContentTagResponse;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.service.ContentTagGroupDomainService;
import allchive.server.domain.domains.content.service.TagDomainService;
import allchive.server.domain.domains.content.validator.TagValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateContentUseCase {
    private final ArchivingValidator archivingValidator;
    private final ContentMapper contentMapper;
    private final ContentDomainService contentDomainService;
    private final TagValidator tagValidator;
    private final TagAdaptor tagAdaptor;
    private final TagDomainService tagDomainService;
    private final ContentTagGroupDomainService contentTagGroupDomainService;
    private final ArchivingDomainService archivingDomainService;

    @Transactional
    public ContentTagResponse execute(CreateContentRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(userId, request);
        Content content = contentMapper.toEntity(request);
        updateTagUsedAt(request.getTagIds());
        List<ContentTagGroup> contentTagGroupList =
                createContentTagGroup(content, request.getTagIds());
        contentDomainService.save(content);
        archivingDomainService.updateContentCnt(
                request.getArchivingId(), request.getContentType(), PLUS_ONE);
        return contentMapper.toContentTagResponse(content, contentTagGroupList, true, userId);
    }

    private void validateExecution(Long userId, CreateContentRequest request) {
        archivingValidator.verifyUser(userId, request.getArchivingId());
        tagValidator.validateExistTagsAndUser(request.getTagIds(), userId);
    }

    private List<ContentTagGroup> createContentTagGroup(Content content, List<Long> tagIds) {
        List<Tag> tags = tagAdaptor.queryTagByTagIdIn(tagIds);
        List<ContentTagGroup> contentTagGroupList =
                contentMapper.toContentTagGroupEntityList(content, tags);
        contentTagGroupDomainService.saveAll(contentTagGroupList);
        return contentTagGroupList;
    }

    private void updateTagUsedAt(List<Long> tagIds) {
        tagAdaptor.queryTagByTagIdIn(tagIds).forEach(tagDomainService::updateUsedAt);
    }
}
