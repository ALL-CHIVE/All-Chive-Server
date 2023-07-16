package allchive.server.api.block.service;

import allchive.server.api.block.model.dto.response.BlockUsersResponse;
import allchive.server.api.block.model.mapper.BlockMapper;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.block.adaptor.BlockAdaptor;
import allchive.server.domain.domains.block.domain.Block;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetBlockUseCase {
    private final BlockAdaptor blockAdaptor;
    private final BlockMapper blockMapper;
    private final UserAdaptor  userAdaptor;

    @Transactional(readOnly = true)
    public BlockUsersResponse execute() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Block> blockList = blockAdaptor.findByBlockFrom(userId);
        List<Long> userIds = blockList.stream().map(Block::getBlockUser).toList();
        List<User> users = userAdaptor.findAllByIdIn(userIds);
        return BlockUsersResponse.from(blockMapper.toBlockUserVoList(blockList, users));
    }
}
