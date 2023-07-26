package allchive.server.domain.domains.search.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.search.exception.SearchErrorCode;

public class NoAuthorityUpdateLatestSearchException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NoAuthorityUpdateLatestSearchException();

    private NoAuthorityUpdateLatestSearchException() {
        super(SearchErrorCode.NO_AUTHORITY_UPDATE_LATEST_SEARCH);
    }
}
