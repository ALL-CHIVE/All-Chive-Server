package allchive.server.domain.domains.content.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.category.exception.CategoryErrorCode;
import allchive.server.domain.domains.content.exception.ContentErrorCode;

public class ContentNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new ContentNotFoundException();

    private ContentNotFoundException() {
        super(ContentErrorCode.CONTENT_NOT_FOUND);
    }
}
