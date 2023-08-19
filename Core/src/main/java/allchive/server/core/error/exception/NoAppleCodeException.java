package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class NoAppleCodeException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NoAppleCodeException();

    private NoAppleCodeException() {
        super(GlobalErrorCode.NO_APPLE_CODE);
    }
}
