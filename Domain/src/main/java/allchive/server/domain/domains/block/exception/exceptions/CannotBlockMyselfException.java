package allchive.server.domain.domains.block.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.block.exception.BlockErrorCode;

public class CannotBlockMyselfException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new CannotBlockMyselfException();

    private CannotBlockMyselfException() {
        super(BlockErrorCode.CANNOT_BLOCK_MYSELF);
    }
}
