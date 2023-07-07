package allchive.server.api.category.service;


import allchive.server.api.category.model.dto.request.UpdateCategoryRequest;
import allchive.server.api.common.util.UrlUtil;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.service.CategoryDomainService;
import allchive.server.domain.domains.category.validator.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateCategoryUseCase {
    private final CategoryDomainService categoryDomainService;
    private final CategoryAdaptor categoryAdaptor;
    private final CategoryValidator categoryValidator;

    @Transactional
    public void execute(Long categoryId, UpdateCategoryRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        categoryValidator.verifyUser(userId, categoryId);
        Category category = categoryAdaptor.findById(categoryId);
        categoryDomainService.updateCategory(
                category,
                request.getTitle(),
                UrlUtil.convertUrlToKey(request.getImageUrl()),
                request.isPublicStatus(),
                request.getSubject());
    }
}
