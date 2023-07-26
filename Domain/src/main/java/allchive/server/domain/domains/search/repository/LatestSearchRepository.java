package allchive.server.domain.domains.search.repository;


import allchive.server.domain.domains.search.domain.LatestSearch;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LatestSearchRepository extends JpaRepository<LatestSearch, Long> {
    List<LatestSearch> findAllByUserIdOrderByCreatedAt(Long userId);

    void deleteAllByUserId(Long userId);

    Optional<LatestSearch> findByIdAndUserId(Long latestSearchId, Long userId);

    void deleteByIdAndUserId(Long latestSearchId, Long userId);
}
