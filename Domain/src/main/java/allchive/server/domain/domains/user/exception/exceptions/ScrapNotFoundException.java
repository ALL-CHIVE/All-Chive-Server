package allchive.server.domain.domains.user.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.user.exception.UserErrorCode;

public class ScrapNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new ScrapNotFoundException();

    private ScrapNotFoundException() {
        super(UserErrorCode.SCRAP_NOT_FOUND);
    }
}
