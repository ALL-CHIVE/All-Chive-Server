package allchive.server.domain.domains.content.repository;

import allchive.server.domain.domains.content.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
