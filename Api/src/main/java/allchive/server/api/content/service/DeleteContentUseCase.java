package allchive.server.api.content.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.recycle.model.mapper.RecycleMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.validator.CategoryValidator;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.domain.enums.RecycleType;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteContentUseCase {
    private final ContentAdaptor contentAdaptor;
    private final CategoryValidator categoryValidator;
    private final ContentDomainService contentDomainService;
    private final RecycleMapper recycleMapper;
    private final RecycleDomainService recycleDomainService;

    @Transactional
    public void execute(Long contentId) {
        Long userId = SecurityUtil.getCurrentUserId();
        Content content = contentAdaptor.findById(contentId);
        categoryValidator.validateCategoryUser(content.getCategoryId(), userId);
        contentDomainService.deleteById(contentId);
        Recycle recycle =
                recycleMapper.toContentRecycleEntity(userId, contentId, RecycleType.CONTENT);
        recycleDomainService.save(recycle);
    }
}
