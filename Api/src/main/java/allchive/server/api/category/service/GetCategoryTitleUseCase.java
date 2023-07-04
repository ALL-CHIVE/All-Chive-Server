package allchive.server.api.category.service;


import allchive.server.api.category.model.dto.response.CategoryTitleResponse;
import allchive.server.api.category.model.mapper.CategoryMapper;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetCategoryTitleUseCase {
    private final CategoryAdaptor categoryAdaptor;
    private final CategoryMapper categoryMapper;

    public CategoryTitleResponse execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        return categoryMapper.toCategoryTitleResponse(
                categoryAdaptor.findAllByUserIdOrderByTopic(userId));
    }
}
