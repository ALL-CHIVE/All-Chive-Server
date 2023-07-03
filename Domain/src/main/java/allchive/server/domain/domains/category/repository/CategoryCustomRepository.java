package allchive.server.domain.domains.category.repository;


import allchive.server.domain.domains.category.domain.Category;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CategoryCustomRepository {
    Slice<Category> querySliceCategoryExceptBlock(
            List<Long> blockList, Long userId, Pageable pageable);

    Slice<Category> querySliceCategoryByUserId(Long userId, Pageable pageable);
}
