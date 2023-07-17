package allchive.server.api.block.model.mapper;


import allchive.server.api.block.model.vo.BlockUserVo;
import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.block.domain.Block;
import allchive.server.domain.domains.user.domain.User;
import java.util.List;

@Mapper
public class BlockMapper {
    public Block toEntity(Long blockFrom, Long blockUser) {
        return Block.of(blockFrom, blockUser);
    }

    public List<BlockUserVo> toBlockUserVoList(List<Block> blockList, List<User> users) {
        return blockList.stream()
                .map(
                        block -> {
                            User user =
                                    users.stream()
                                            .filter(u -> u.getId().equals(block.getBlockUser()))
                                            .findFirst()
                                            .orElseThrow();
                            return BlockUserVo.of(user.getNickname(), block.getBlockUser());
                        })
                .toList();
    }
}
