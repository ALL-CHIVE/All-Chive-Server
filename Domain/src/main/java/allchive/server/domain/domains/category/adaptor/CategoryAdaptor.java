package allchive.server.domain.domains.category.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.core.error.exception.InternalServerError;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.domain.enums.Topic;
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
        if (category.getTopic().equals(Topic.ALL)) {
            throw InternalServerError.EXCEPTION;
        }
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
            List<Long> categoryIdList, List<Long> blockList, Topic topic, Pageable pageable) {
        return categoryRepository.querySliceCategoryExceptBlock(
                categoryIdList, blockList, topic, pageable);
    }

    public Slice<Category> querySliceCategoryByUserId(Long userId, Topic topic, Pageable pageable) {
        return categoryRepository.querySliceCategoryByUserId(userId, topic, pageable);
    }

    public Slice<Category> querySliceCategoryIn(
            List<Long> categoryIdList, Topic topic, Pageable pageable) {
        return categoryRepository.querySliceCategoryIn(categoryIdList, topic, pageable);
    }

    public List<Category> queryCategoryByUserId(Long userId) {
        return categoryRepository.queryCategoryByUserId(userId);
    }

    public boolean queryCategoryExist(Long categoryId) {
        return categoryRepository.queryCategoryExist(categoryId);
    }
}
