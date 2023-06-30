package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class ExampleException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new ExampleException();

    private ExampleException() {
        super(GlobalErrorCode.EXAMPLE_ERROR);
    }
}
