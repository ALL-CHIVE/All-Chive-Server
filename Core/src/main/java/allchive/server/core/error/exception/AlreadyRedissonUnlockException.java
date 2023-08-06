package allchive.server.core.error.exception;


import allchive.server.core.error.BaseErrorException;
import allchive.server.core.error.GlobalErrorCode;

public class AlreadyRedissonUnlockException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new AlreadyRedissonUnlockException();

    private AlreadyRedissonUnlockException() {
        super(GlobalErrorCode.ALREADY_REDISSON_UNLOCK);
    }
}
