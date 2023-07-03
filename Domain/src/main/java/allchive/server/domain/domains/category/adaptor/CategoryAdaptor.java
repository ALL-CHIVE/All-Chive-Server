package allchive.server.domain.domains.category.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.exception.exceptions.CategoryNotFoundException;
import allchive.server.domain.domains.category.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@Adaptor
@RequiredArgsConstructor
public class CategoryAdaptor {
    private final CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category findById(Long categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }

    public void deleteById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public Slice<Category> querySliceCategoryExceptBlock(
            List<Long> blockList, Long userId, Pageable pageable) {
        return categoryRepository.querySliceCategoryExceptBlock(blockList, userId, pageable);
    }

    public Slice<Category> querySliceCategoryByUserId(Long userId, Pageable pageable) {
        return categoryRepository.querySliceCategoryByUserId(userId, pageable);
    }
}
