package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class InterruptRedissonException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new InterruptRedissonException();

    private InterruptRedissonException() {
        super(GlobalErrorCode.INTERRUPTED_REDISSON);
    }
}
