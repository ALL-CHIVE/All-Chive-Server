package allchive.server.domain.domains.content.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.content.exception.ContentErrorCode;

public class TagNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new TagNotFoundException();

    private TagNotFoundException() {
        super(ContentErrorCode.TAG_NOT_FOUND);
    }
}
