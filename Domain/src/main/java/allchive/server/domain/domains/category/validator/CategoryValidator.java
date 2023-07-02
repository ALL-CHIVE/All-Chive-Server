package allchive.server.domain.domains.category.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;

@Validator
@RequiredArgsConstructor
public class CategoryValidator {
    private final CategoryAdaptor categoryAdaptor;

    public void verifyUser(Long userId, Long categoryId) {
        categoryAdaptor.findById(categoryId).validateUser(userId);
    }
}
