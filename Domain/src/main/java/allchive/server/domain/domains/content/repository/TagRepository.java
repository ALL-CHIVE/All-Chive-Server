package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>, TagCustomRepository {
    List<Tag> findAllByUserIdOrderByCreatedAtDesc(Long userId);

    List<Tag> findAllByUserId(Long userId);
}
