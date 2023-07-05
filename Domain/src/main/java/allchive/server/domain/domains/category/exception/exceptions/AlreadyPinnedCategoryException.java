package allchive.server.domain.domains.category.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.category.exception.CategoryErrorCode;

public class AlreadyPinnedCategoryException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new AlreadyPinnedCategoryException();

    private AlreadyPinnedCategoryException() {
        super(CategoryErrorCode.ALREADY_PINNED_CATEGORY);
    }
}
