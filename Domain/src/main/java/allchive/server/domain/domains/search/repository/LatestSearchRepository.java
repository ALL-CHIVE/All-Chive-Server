package allchive.server.domain.domains.search.repository;


import allchive.server.domain.domains.search.domain.LatestSearch;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LatestSearchRepository extends JpaRepository<LatestSearch, Long> {
    List<LatestSearch> findAllByUserIdOrderByCreatedAt(Long userId);
}
