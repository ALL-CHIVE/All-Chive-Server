package allchive.server.domain.domains.block.validator;


import allchive.server.core.annotation.Validator;
import allchive.server.domain.domains.block.adaptor.BlockAdaptor;
import allchive.server.domain.domains.block.exception.exceptions.CannotBlockMyselfException;
import allchive.server.domain.domains.block.exception.exceptions.DuplicatedBlockException;
import lombok.RequiredArgsConstructor;

@Validator
@RequiredArgsConstructor
public class BlockValidator {
    private final BlockAdaptor blockAdaptor;

    public void validateNotDuplicate(Long blockFrom, Long blockUser) {
        if (blockAdaptor.queryBlockExistByBlockFromAndBlockUser(blockFrom, blockUser)) {
            throw DuplicatedBlockException.EXCEPTION;
        }
    }

    public void validateNotMyself(Long blockFrom, Long blockUser) {
        if (blockFrom.equals(blockUser)) {
            throw CannotBlockMyselfException.EXCEPTION;
        }
    }
}
