package allchive.server.domain.domains.user.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.user.exception.UserErrorCode;

public class UserNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
