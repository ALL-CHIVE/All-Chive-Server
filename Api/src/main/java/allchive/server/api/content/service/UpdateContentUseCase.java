package allchive.server.api.content.service;

import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.request.UpdateContentRequest;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.adaptor.ContentTagGroupAdaptor;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.service.ContentTagGroupDomainService;
import allchive.server.domain.domains.content.validator.ContentValidator;
import allchive.server.domain.domains.content.validator.TagValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public void execute(Long contentId, UpdateContentRequest request) {
        validateExecution(contentId, request);
        regenerateContentTagGroup(contentId, request.getTagIds());
        contentDomainService.update(contentId, request.getContentType(), request.getArchivingId(),
                request.getLink(), request.getMemo(), UrlUtil.convertUrlToKey(request.getImgUrl()),
                request.getTitle());
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
}
