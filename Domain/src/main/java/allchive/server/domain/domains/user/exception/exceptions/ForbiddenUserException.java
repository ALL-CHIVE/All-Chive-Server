package allchive.server.domain.domains.user.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.user.exception.UserErrorCode;

public class ForbiddenUserException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new ForbiddenUserException();

    private ForbiddenUserException() {
        super(UserErrorCode.FORBIDDEN_USER);
    }
}
