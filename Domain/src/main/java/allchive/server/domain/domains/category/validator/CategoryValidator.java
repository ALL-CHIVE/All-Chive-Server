package allchive.server.domain.domains.category.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.category.adaptor.CategoryAdaptor;
import allchive.server.domain.domains.category.exception.exceptions.AlreadyPinnedCategoryException;
import allchive.server.domain.domains.category.exception.exceptions.CategoryNotFoundException;
import allchive.server.domain.domains.category.exception.exceptions.NotPinnedCategoryException;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class CategoryValidator {
    private final CategoryAdaptor categoryAdaptor;

    public void verifyUser(Long userId, Long categoryId) {
        categoryAdaptor.findById(categoryId).validateUser(userId);
    }

    public void validatePublicStatus(Long categoryId, Long userId) {
        categoryAdaptor.findById(categoryId).validatePublicStatus(userId);
    }

    public void validateDeleteStatus(Long categoryId, Long userId) {
        categoryAdaptor.findById(categoryId).validateDeleteStatus(userId);
    }

    public void validateExistCategory(Long categoryId) {
        if (!categoryAdaptor.queryCategoryExist(categoryId)) {
            throw CategoryNotFoundException.EXCEPTION;
        }
    }

    public void validateAlreadyPinStatus(Long categoryId, Long userId) {
        if (categoryAdaptor.findById(categoryId).getPinUserId().contains(userId)) {
            throw AlreadyPinnedCategoryException.EXCEPTION;
        }
    }

    public void validateNotPinStatus(Long categoryId, Long userId) {
        if (!categoryAdaptor.findById(categoryId).getPinUserId().contains(userId)) {
            throw NotPinnedCategoryException.EXCEPTION;
        }
    }

    public void validateCategoryUser(Long categoryId, Long userId) {
        categoryAdaptor.findById(categoryId).validateUser(userId);
    }
}
