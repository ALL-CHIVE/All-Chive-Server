package allchive.server.domain.domains.category.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.category.exception.CategoryErrorCode;

public class CategoryNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new CategoryNotFoundException();

    private CategoryNotFoundException() {
        super(CategoryErrorCode.CATEGORY_NOT_FOUND);
    }
}
