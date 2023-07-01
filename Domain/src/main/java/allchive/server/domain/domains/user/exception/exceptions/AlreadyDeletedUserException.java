package allchive.server.domain.domains.user.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.user.exception.UserErrorCode;

public class AlreadyDeletedUserException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new AlreadyDeletedUserException();

    private AlreadyDeletedUserException() {
        super(UserErrorCode.USER_ALREADY_DELETED);
    }
}
