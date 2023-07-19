package allchive.server.domain.domains.block.adaptor;


import allchive.server.core.annotation.Adaptor;
import allchive.server.domain.domains.block.domain.Block;
import allchive.server.domain.domains.block.repository.BlockRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class BlockAdaptor {
    private final BlockRepository blockRepository;

    public List<Block> findByBlockFrom(Long userId) {
        return blockRepository.findAllByBlockFrom(userId);
    }

    public boolean queryBlockExistByBlockFromAndBlockUser(Long blockFrom, Long blockUser) {
        return blockRepository.queryBlockExistByBlockFromAndBlockUser(blockFrom, blockUser);
    }

    public void save(Block block) {
        blockRepository.save(block);
    }

    public void deleteByBlockFromAndBlockUser(Long blockFrom, Long blockUser) {
        blockRepository.deleteByBlockFromAndBlockUser(blockFrom, blockUser);
    }

    public void queryDeleteBlockByBlockFromOrBlockUser(Long userId) {
        blockRepository.queryDeleteBlockByBlockFromOrBlockUser(userId);
    }
}
