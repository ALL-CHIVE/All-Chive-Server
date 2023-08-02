package allchive.server.domain.domains.content.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.content.exception.ContentErrorCode;

public class AlreadyDeletedContentException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new AlreadyDeletedContentException();

    private AlreadyDeletedContentException() {
        super(ContentErrorCode.ALREADY_DELETED_CONTENT);
    }
}
