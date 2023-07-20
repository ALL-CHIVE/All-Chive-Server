package allchive.server.domain.domains.content.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.content.exception.ContentErrorCode;

public class NoAuthorityUpdateContentException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NoAuthorityUpdateContentException();

    private NoAuthorityUpdateContentException() {
        super(ContentErrorCode.NO_AUTHORITY_UPDATE_CONTENT);
    }
}
