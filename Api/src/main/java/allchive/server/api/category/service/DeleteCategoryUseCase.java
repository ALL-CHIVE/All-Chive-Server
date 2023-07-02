package allchive.server.api.category.service;

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
public class DeleteCategoryUseCase {
    private final CategoryDomainService categoryDomainService;
    private final CategoryValidator categoryValidator;

    @Transactional
    public void execute(Long categoryId) {
        Long userId = SecurityUtil.getCurrentUserId();
        categoryValidator.verifyUser(userId, categoryId);
        categoryDomainService.deleteCategory(categoryId);
    }
}
