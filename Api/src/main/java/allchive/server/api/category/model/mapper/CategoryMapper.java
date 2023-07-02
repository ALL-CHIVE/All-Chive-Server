package allchive.server.api.category.model.mapper;

import allchive.server.api.category.model.dto.request.CreateCategoryRequest;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.category.domain.Category;

@Mapper
public class CategoryMapper {
    public Category toEntity(CreateCategoryRequest request, Long userId) {
        return Category.of(userId, request.getTitle(), request.isPublicStatus(), request.getTopic());
    }
}
