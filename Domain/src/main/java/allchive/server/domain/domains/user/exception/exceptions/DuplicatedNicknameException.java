package allchive.server.domain.domains.user.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.user.exception.UserErrorCode;

public class DuplicatedNicknameException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new DuplicatedNicknameException();

    private DuplicatedNicknameException() {
        super(UserErrorCode.DUPLICATED_NICKNAME);
    }
}
