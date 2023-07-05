package allchive.server.api.category.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.service.CategoryDomainService;
import allchive.server.domain.domains.category.validator.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateCategoryPinUseCase {
    private final CategoryValidator categoryValidator;
    private final CategoryDomainService categoryDomainService;

    @Transactional
    public void execute(Long categoryId, Boolean cancel) {
        Long userId = SecurityUtil.getCurrentUserId();
        categoryValidator.validateExistCategory(categoryId);
        categoryValidator.validateDeleteStatus(categoryId, userId);
        if (cancel) {
            categoryValidator.validateNotPinStatus(categoryId, userId);
            categoryDomainService.updatePin(categoryId, userId, false);
        } else {
            categoryValidator.validateAlreadyPinStatus(categoryId, userId);
            categoryDomainService.updatePin(categoryId, userId, true);
        }
    }
}
