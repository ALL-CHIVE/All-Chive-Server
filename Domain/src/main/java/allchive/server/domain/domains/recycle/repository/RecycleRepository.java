package allchive.server.domain.domains.recycle.repository;


import allchive.server.domain.domains.recycle.domain.Recycle;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecycleRepository extends JpaRepository<Recycle, Long>, RecycleCustomRepository {
    List<Recycle> findAllByUserId(Long userId);

    List<Recycle> findAllByDeletedAtBefore(LocalDateTime time);
}
