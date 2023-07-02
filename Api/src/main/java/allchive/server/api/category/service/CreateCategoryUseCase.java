package allchive.server.api.category.service;

import allchive.server.api.category.model.dto.request.CreateCategoryRequest;
import allchive.server.api.category.model.mapper.CategoryMapper;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.service.CategoryDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateCategoryUseCase {
    private final CategoryAdaptor categoryAdaptor;
    private final CategoryMapper categoryMapper;
    private final CategoryDomainService categoryDomainService;

    public void execute(CreateCategoryRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        final Category category = categoryMapper.toEntity(request, userId);
        categoryDomainService.createCategory(category);
    }
}
