package allchive.server.domain.domains.category.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.category.exception.CategoryErrorCode;

public class DeletedCategoryException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new DeletedCategoryException();

    private DeletedCategoryException() {
        super(CategoryErrorCode.DELETED_CATEGORY);
    }
}
