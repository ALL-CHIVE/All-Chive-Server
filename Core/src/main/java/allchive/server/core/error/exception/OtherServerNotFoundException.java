package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class OtherServerNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new OtherServerNotFoundException();

    private OtherServerNotFoundException() {
        super(GlobalErrorCode.OTHER_SERVER_NOT_FOUND);
    }
}
