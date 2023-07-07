package allchive.server.api.category.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.api.recycle.model.mapper.RecycleMapper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.service.CategoryDomainService;
import allchive.server.domain.domains.category.validator.CategoryValidator;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.domain.enums.RecycleType;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteCategoryUseCase {
    private final CategoryDomainService categoryDomainService;
    private final CategoryValidator categoryValidator;
    private final RecycleMapper recycleMapper;
    private final RecycleDomainService recycleDomainService;

    @Transactional
    public void execute(Long categoryId) {
        Long userId = SecurityUtil.getCurrentUserId();
        categoryValidator.verifyUser(userId, categoryId);
        categoryDomainService.deleteById(categoryId);
        Recycle recycle = recycleMapper.toCategoryRecycleEntity(userId, categoryId, RecycleType.CATEGORY);
        recycleDomainService.save(recycle);
    }
}
