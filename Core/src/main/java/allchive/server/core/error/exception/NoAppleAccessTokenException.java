package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class NoAppleAccessTokenException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NoAppleAccessTokenException();

    private NoAppleAccessTokenException() {
        super(GlobalErrorCode.NO_APPLE_ACCESS_TOKEN);
    }
}
