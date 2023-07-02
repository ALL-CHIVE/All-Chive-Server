package allchive.server.domain.domains.category.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.exception.exceptions.CategoryNotFoundException;
import allchive.server.domain.domains.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class CategoryAdaptor {
    private final CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(()-> CategoryNotFoundException.EXCEPTION);
    }
}
