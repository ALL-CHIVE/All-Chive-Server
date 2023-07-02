package allchive.server.domain.domains.category.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.Category;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CategoryDomainService {
    private final CategoryAdaptor categoryAdaptor;

    public void createCategory(Category category) {
        categoryAdaptor.save(category);
    }
}
