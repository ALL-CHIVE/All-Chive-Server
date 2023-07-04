package allchive.server.domain.domains.user.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.user.exception.UserErrorCode;

public class AlreadyExistScrapException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new AlreadyExistScrapException();

    private AlreadyExistScrapException() {
        super(UserErrorCode.ALREADY_EXIST_SCRAP);
    }
}
