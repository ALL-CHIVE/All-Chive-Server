package allchive.server.domain.domains.category.repository;


import allchive.server.domain.domains.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository
        extends JpaRepository<Category, Long>, CategoryCustomRepository {
    List<Category> findAllByUserId(Long userId);
}
