package allchive.server.domain.domains.category.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.category.exception.CategoryErrorCode;

public class NotPinnedCategoryException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NotPinnedCategoryException();

    private NotPinnedCategoryException() {
        super(CategoryErrorCode.NOT_PINNED_CATEGORY);
    }
}
