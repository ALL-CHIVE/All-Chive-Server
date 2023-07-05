package allchive.server.api.category.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.service.CategoryDomainService;
import allchive.server.domain.domains.category.validator.CategoryValidator;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.service.ScrapDomainService;
import allchive.server.domain.domains.user.validator.ScrapValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateCategoryScrapUseCase {
    private final CategoryValidator categoryValidator;
    private final UserAdaptor userAdaptor;
    private final ScrapDomainService scrapDomainService;
    private final CategoryDomainService categoryDomainService;
    private final ScrapValidator scrapValidator;

    @Transactional
    public void execute(Long categoryId, Boolean cancel) {
        categoryValidator.validateExistCategory(categoryId);
        Long userId = SecurityUtil.getCurrentUserId();
        categoryValidator.validateDeleteStatus(categoryId, userId);
        User user = userAdaptor.queryUserById(userId);
        if (cancel) {
            scrapDomainService.deleteScrapByUserAndCategoryId(user, categoryId);
            categoryDomainService.updateScrapCount(categoryId, -1);
        } else {
            scrapValidator.validateExistScrap(user, categoryId);
            Scrap scrap = Scrap.of(user, categoryId);
            scrapDomainService.save(scrap);
            categoryDomainService.updateScrapCount(categoryId, 1);
        }
    }
}
