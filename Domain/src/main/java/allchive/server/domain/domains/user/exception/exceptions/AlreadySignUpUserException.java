package allchive.server.domain.domains.user.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.user.exception.UserErrorCode;

public class AlreadySignUpUserException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new AlreadySignUpUserException();

    private AlreadySignUpUserException() {
        super(UserErrorCode.USER_ALREADY_SIGNUP);
    }
}
