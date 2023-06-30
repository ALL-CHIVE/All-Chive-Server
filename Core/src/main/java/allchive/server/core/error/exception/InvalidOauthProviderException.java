package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class InvalidOauthProviderException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new InvalidOauthProviderException();

    private InvalidOauthProviderException() {
        super(GlobalErrorCode.INVALID_OAUTH_PROVIDER);
    }
}
