package allchive.server.domain.domains.content.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.content.exception.ContentErrorCode;

public class NoAuthorityUpdateException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NoAuthorityUpdateException();

    private NoAuthorityUpdateException() {
        super(ContentErrorCode.NO_AUTHORITY_UPDATE_TAG);
    }
}
