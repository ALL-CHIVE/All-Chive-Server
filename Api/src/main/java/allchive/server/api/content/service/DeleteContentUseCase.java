package allchive.server.api.content.service;

import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.content.model.dto.response.ContentTagResponse;
import allchive.server.api.content.model.mapper.ContentMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.validator.CategoryValidator;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.adaptor.ContentTagGroupAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.content.service.ContentDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class DeleteContentUseCase {
    private final ContentAdaptor contentAdaptor;
    private final CategoryValidator categoryValidator;
    private final ContentDomainService contentDomainService;

    // TODO : 휴지통 처리 해야함
    public void execute(Long contentId) {
        Long userId = SecurityUtil.getCurrentUserId();
        Content content = contentAdaptor.findById(contentId);
        categoryValidator.validateCategoryUser(content.getCategoryId(), userId);
        contentDomainService.deleteById(contentId);
    }
}
