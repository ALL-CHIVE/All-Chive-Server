package allchive.server.domain.domains.category.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.domain.enums.Topic;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CategoryDomainService {
    private final CategoryAdaptor categoryAdaptor;

    public void createCategory(Category category) {
        categoryAdaptor.save(category);
    }

    public void updateCategory(
            Category category, String title, String imageUrl, boolean publicStatus, Topic topic) {
        category.update(title, imageUrl, publicStatus, topic);
        categoryAdaptor.save(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryAdaptor.deleteById(categoryId);
    }
}
