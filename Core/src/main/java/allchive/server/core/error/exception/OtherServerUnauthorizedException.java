package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class OtherServerUnauthorizedException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new OtherServerUnauthorizedException();

    private OtherServerUnauthorizedException() {
        super(GlobalErrorCode.OTHER_SERVER_UNAUTHORIZED);
    }
}
