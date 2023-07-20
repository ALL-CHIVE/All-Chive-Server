package allchive.server.domain.domains.content.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.content.exception.ContentErrorCode;

public class NoAuthorityUpdateTagException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NoAuthorityUpdateTagException();

    private NoAuthorityUpdateTagException() {
        super(ContentErrorCode.NO_AUTHORITY_UPDATE_TAG);
    }
}
