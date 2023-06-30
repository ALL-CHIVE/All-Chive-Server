package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class InternalServerError extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new InternalServerError();

    private InternalServerError() {
        super(GlobalErrorCode._INTERNAL_SERVER_ERROR);
    }
}
