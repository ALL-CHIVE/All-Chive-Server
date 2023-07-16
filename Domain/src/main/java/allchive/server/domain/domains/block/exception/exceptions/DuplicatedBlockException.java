package allchive.server.domain.domains.block.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.block.exception.BlockErrorCode;

public class DuplicatedBlockException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new DuplicatedBlockException();

    private DuplicatedBlockException() {
        super(BlockErrorCode.DUPLICATED_BLOCK);
    }
}
