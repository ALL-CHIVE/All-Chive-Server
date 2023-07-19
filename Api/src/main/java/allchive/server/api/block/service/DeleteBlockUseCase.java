package allchive.server.api.block.service;


import allchive.server.api.block.model.dto.request.BlockRequest;
import allchive.server.api.block.model.dto.response.BlockResponse;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.block.service.BlockDomainService;
import allchive.server.domain.domains.block.validator.BlockValidator;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteBlockUseCase {
    private final BlockValidator blockValidator;
    private final BlockDomainService blockDomainService;
    private final UserAdaptor userAdaptor;

    @Transactional
    public BlockResponse execute(BlockRequest request) {
        Long userId = SecurityUtil.getCurrentUserId();
        blockValidator.validateExist(userId, request.getUserId());
        blockDomainService.deleteByBlockFromAndBlockUser(userId, request.getUserId());
        return BlockResponse.from(userAdaptor.findUserById(request.getUserId()).getNickname());
    }
}
