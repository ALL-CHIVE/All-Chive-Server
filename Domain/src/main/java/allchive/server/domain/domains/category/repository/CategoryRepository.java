package allchive.server.domain.domains.category.repository;

import allchive.server.domain.domains.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
