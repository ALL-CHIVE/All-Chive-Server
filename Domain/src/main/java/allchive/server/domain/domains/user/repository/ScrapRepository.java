package allchive.server.domain.domains.user.repository;


import allchive.server.domain.domains.user.domain.Scrap;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findAllByUserId(Long userId);
}
