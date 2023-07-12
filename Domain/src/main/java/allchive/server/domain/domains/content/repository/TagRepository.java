package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long>, TagCustomRepository {
    List<Tag> findAllByUserIdOrderByUsedAtDesc(Long userId);

    List<Tag> findAllByUserIdOrderByCreatedAtDesc(Long userId);
}
