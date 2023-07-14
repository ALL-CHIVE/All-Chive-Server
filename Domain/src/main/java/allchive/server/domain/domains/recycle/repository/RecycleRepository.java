package allchive.server.domain.domains.recycle.repository;


import allchive.server.domain.domains.recycle.domain.Recycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecycleRepository extends JpaRepository<Recycle, Long>, RecycleCustomRepository {
    List<Recycle> findAllByUserId(Long userId);
}
