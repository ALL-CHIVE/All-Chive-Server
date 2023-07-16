package allchive.server.domain.domains.block.repository;


import allchive.server.domain.domains.block.domain.Block;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long>, BlockCustomRepository {
    List<Block> findAllByBlockFrom(Long userId);
}
