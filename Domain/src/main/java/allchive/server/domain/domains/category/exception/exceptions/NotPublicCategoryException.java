package allchive.server.domain.domains.category.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.category.exception.CategoryErrorCode;

public class NotPublicCategoryException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NotPublicCategoryException();

    private NotPublicCategoryException() {
        super(CategoryErrorCode.NOT_PUBLIC_CATEGORY);
    }
}
