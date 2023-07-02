package allchive.server.domain.domains.category.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.category.exception.CategoryErrorCode;

public class NoAuthurityUpdateCategoryException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NoAuthurityUpdateCategoryException();

    private NoAuthurityUpdateCategoryException() {
        super(CategoryErrorCode.NO_AUTHORITY_UPDATE_CATEGORY);
    }
}
