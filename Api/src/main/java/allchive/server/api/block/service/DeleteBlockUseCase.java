package allchive.server.api.block.service;


import allchive.server.api.block.model.dto.request.BlockRequest;
import allchive.server.api.block.model.dto.response.BlockResponse;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.common.aop.distributedLock.DistributedLock;
import allchive.server.domain.common.enums.DistributedLockType;
import allchive.server.domain.domains.block.service.BlockDomainService;
import allchive.server.domain.domains.block.validator.BlockValidator;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteBlockUseCase {
    private final UserValidator userValidator;
    private final BlockValidator blockValidator;
    private final BlockDomainService blockDomainService;
    private final UserAdaptor userAdaptor;

    @Transactional
    @DistributedLock(
            lockType = DistributedLockType.BLOCK,
            identifier = {"fromUserId", "toUserId"})
    public BlockResponse execute(BlockRequest request, Long fromUserId, Long toUserId) {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(userId, request);
        blockDomainService.deleteByBlockFromAndBlockUser(userId, request.getUserId());
        return BlockResponse.from(userAdaptor.findById(request.getUserId()).getNickname());
    }

    private void validateExecution(Long userId, BlockRequest request) {
        userValidator.validateExist(request.getUserId());
        blockValidator.validateExist(userId, request.getUserId());
    }
}
