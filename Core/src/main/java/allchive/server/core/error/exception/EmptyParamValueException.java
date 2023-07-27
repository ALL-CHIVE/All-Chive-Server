package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class EmptyParamValueException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new EmptyParamValueException();

    private EmptyParamValueException() {
        super(GlobalErrorCode.EMPTY_PARAM_VALUE);
    }
}
