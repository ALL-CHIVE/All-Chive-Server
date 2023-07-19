package allchive.server.domain.domains.user.repository;


import allchive.server.domain.domains.user.domain.Scrap;
import allchive.server.domain.domains.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findAllByUserId(Long userId);

    Optional<Scrap> findAllByUserAndArchivingId(User user, Long archivingId);

    void deleteAllByArchivingIdIn(List<Long> archivingId);

    void deleteAllByUser(User user);
}
