package allchive.server.domain.domains.search.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.search.exception.SearchErrorCode;

public class LatestSearchNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new LatestSearchNotFoundException();

    private LatestSearchNotFoundException() {
        super(SearchErrorCode.LATEST_SEARCH_NOT_FOUND);
    }
}
