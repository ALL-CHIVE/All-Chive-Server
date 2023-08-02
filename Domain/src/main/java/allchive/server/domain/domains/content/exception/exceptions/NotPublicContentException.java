package allchive.server.domain.domains.content.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.content.exception.ContentErrorCode;

public class NotPublicContentException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NotPublicContentException();

    private NotPublicContentException() {
        super(ContentErrorCode.NOT_PUBLIC_CONTENT);
    }
}
