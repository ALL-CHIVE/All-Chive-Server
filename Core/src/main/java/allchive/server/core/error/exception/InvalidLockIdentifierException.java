package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class InvalidLockIdentifierException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new InvalidLockIdentifierException();

    private InvalidLockIdentifierException() {
        super(GlobalErrorCode.INVALID_LOCK_IDENTIFIER);
    }
}
