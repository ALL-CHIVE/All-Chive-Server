package allchive.server.api.content.service;

import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.request.CreateContentRequest;
import allchive.server.api.content.model.dto.response.ContentTagResponse;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.validator.CategoryValidator;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.adaptor.ContentTagGroupAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetContentUseCase {
    private final CategoryValidator categoryValidator;
    private final ContentAdaptor contentAdaptor;
    private final ContentTagGroupAdaptor contentTagGroupAdaptor;
    private final ContentMapper contentMapper;

    @Transactional(readOnly = true)
    public ContentTagResponse execute(Long contentId) {
        Content content = contentAdaptor.findById(contentId);
        Long userId = SecurityUtil.getCurrentUserId();
        categoryValidator.validatePublicStatus(content.getCategoryId(), userId);
        List<ContentTagGroup> contentTagGroupList = contentTagGroupAdaptor.findAllByContent(content);
        return contentMapper.toContentTagResponse(content, contentTagGroupList);
    }
}
