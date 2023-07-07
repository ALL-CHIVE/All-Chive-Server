package allchive.server.domain.domains.category.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.domain.enums.Subject;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CategoryDomainService {
    private final CategoryAdaptor categoryAdaptor;

    public void createCategory(Category category) {
        categoryAdaptor.save(category);
    }

    public void updateCategory(
            Category category,
            String title,
            String imageUrl,
            boolean publicStatus,
            Subject subject) {
        category.update(title, imageUrl, publicStatus, subject);
        categoryAdaptor.save(category);
    }

    public void updateScrapCount(Long categoryId, int i) {
        Category category = categoryAdaptor.findById(categoryId);
        category.updateScrapCnt(i);
        categoryAdaptor.save(category);
    }

    public void updatePin(Long categoryId, Long userId, boolean pin) {
        Category category = categoryAdaptor.findById(categoryId);
        if (pin) {
            category.addPinUserId(userId);
        } else {
            category.deletePinUserId(userId);
        }
        category.updateScrapCnt(pin ? 1 : -1);
    }

    public void deleteById(Long categoryId) {
        Category category = categoryAdaptor.findById(categoryId);
        category.delete();
        categoryAdaptor.save(category);
    }
}
