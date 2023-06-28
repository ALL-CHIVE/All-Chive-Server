package allchive.server.domain.domains.category.repository;

import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.domain.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Long> {
}
