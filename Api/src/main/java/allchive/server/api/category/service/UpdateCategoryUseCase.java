package allchive.server.api.category.service;

import allchive.server.api.category.model.dto.request.UpdateCategoryRequest;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.service.CategoryDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateCategoryUseCase {
    private final CategoryDomainService categoryDomainService;
    private final CategoryAdaptor categoryAdaptor;

    public void execute(Long categoryId, UpdateCategoryRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        Category category = categoryAdaptor.findById(categoryId);
        categoryDomainService.updateCategory(category, request.getTitle(), request.isPublicStatus(), request.getTopic());
    }
}
