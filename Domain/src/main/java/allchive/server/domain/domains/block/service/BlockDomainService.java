package allchive.server.domain.domains.block.service;


import allchive.server.core.annotation.DomainService;
import allchive.server.domain.domains.block.adaptor.BlockAdaptor;
import allchive.server.domain.domains.block.domain.Block;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class BlockDomainService {
    private final BlockAdaptor blockAdaptor;

    public void save(Block block) {
        blockAdaptor.save(block);
    }

    public void deleteByBlockFromAndBlockUser(Long blockFrom, Long blockUser) {
        blockAdaptor.deleteByBlockFromAndBlockUser(blockFrom, blockUser);
    }

    public void queryDeleteBlockByBlockFromOrBlockUser(Long userId) {
        blockAdaptor.queryDeleteBlockByBlockFromOrBlockUser(userId);
    }
}
