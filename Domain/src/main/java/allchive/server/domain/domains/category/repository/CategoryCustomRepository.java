package allchive.server.domain.domains.category.repository;


import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.domain.enums.Topic;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CategoryCustomRepository {
    Slice<Category> querySliceCategoryExceptBlock(
            List<Long> categoryIdList, List<Long> blockList, Topic topic, Pageable pageable);

    Slice<Category> querySliceCategoryByUserId(Long userId, Topic topic, Pageable pageable);

    Slice<Category> querySliceCategoryIn(List<Long> categoryIdList, Topic topic, Pageable pageable);

    List<Category> queryCategoryByUserId(Long userId);
}
