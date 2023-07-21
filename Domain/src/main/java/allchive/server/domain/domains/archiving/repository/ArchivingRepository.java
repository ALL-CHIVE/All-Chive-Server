package allchive.server.domain.domains.archiving.repository;


import allchive.server.domain.domains.archiving.domain.Archiving;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivingRepository
        extends JpaRepository<Archiving, Long>, ArchivingCustomRepository {
    List<Archiving> findAllByUserId(Long userId);

    List<Archiving> findAllByIdIn(List<Long> ids);

    List<Archiving> findAllByPublicStatus(Boolean publicStatus);
}
