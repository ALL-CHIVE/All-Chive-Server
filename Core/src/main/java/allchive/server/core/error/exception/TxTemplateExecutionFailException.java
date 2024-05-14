package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class TxTemplateExecutionFailException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new TxTemplateExecutionFailException();

    private TxTemplateExecutionFailException() {
        super(GlobalErrorCode.FAIL_TO_TRANSACTION_TEMPLATE_EXECUTE_ERROR);
    }
}
