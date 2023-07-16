package allchive.server.domain.domains.block.exception.exceptions;


import allchive.server.core.error.BaseErrorException;
import allchive.server.domain.domains.block.exception.BlockErrorCode;

public class BlockNotFoundException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new BlockNotFoundException();

    private BlockNotFoundException() {
        super(BlockErrorCode.BLOCK_NOT_FOUND);
    }
}
