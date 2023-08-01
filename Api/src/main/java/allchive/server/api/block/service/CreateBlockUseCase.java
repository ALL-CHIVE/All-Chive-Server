package allchive.server.api.block.service;


import allchive.server.api.block.model.dto.request.BlockRequest;
import allchive.server.api.block.model.dto.response.BlockResponse;
import allchive.server.api.block.model.mapper.BlockMapper;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.block.domain.Block;
import allchive.server.domain.domains.block.service.BlockDomainService;
import allchive.server.domain.domains.block.validator.BlockValidator;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateBlockUseCase {
    private final UserValidator userValidator;
    private final BlockValidator blockValidator;
    private final BlockMapper blockMapper;
    private final BlockDomainService blockDomainService;
    private final UserAdaptor userAdaptor;

    @Transactional
    public BlockResponse execute(BlockRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(userId, request);
        Block block = blockMapper.toEntity(userId, request.getUserId());
        blockDomainService.save(block);
        return BlockResponse.from(userAdaptor.findById(request.getUserId()).getNickname());
    }

    private void validateExecution(Long userId, BlockRequest request) {
        userValidator.validateExist(request.getUserId());
        blockValidator.validateNotDuplicate(userId, request.getUserId());
        blockValidator.validateNotMyself(userId, request.getUserId());
    }
}
