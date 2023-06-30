package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class OtherServerBadRequestException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new OtherServerBadRequestException();

    private OtherServerBadRequestException() {
        super(GlobalErrorCode.OTHER_SERVER_BAD_REQUEST);
    }
}
