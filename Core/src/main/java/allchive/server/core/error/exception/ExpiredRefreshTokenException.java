package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class ExpiredRefreshTokenException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new ExpiredRefreshTokenException();

    private ExpiredRefreshTokenException() {
        super(GlobalErrorCode.EXPIRED_REFRESH_TOKEN);
    }
}
