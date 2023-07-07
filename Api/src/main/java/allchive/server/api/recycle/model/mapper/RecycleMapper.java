package allchive.server.api.recycle.model.mapper;


import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.domain.enums.RecycleType;

@Mapper
public class RecycleMapper {
    public Recycle toContentRecycleEntity(Long userId, Long contentId, RecycleType type) {
        return Recycle.of(type, contentId, null, userId);
    }

    public Recycle toCategoryRecycleEntity(Long userId, Long categoryId, RecycleType type) {
        return Recycle.of(type, null, categoryId, userId);
    }
}
