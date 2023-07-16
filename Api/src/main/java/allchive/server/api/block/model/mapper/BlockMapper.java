package allchive.server.api.block.model.mapper;


import allchive.server.core.annotation.Mapper;
import allchive.server.domain.domains.block.domain.Block;

@Mapper
public class BlockMapper {
    public Block toEntity(Long blockFrom, Long blockUser) {
        return Block.of(blockFrom, blockUser);
    }
}
