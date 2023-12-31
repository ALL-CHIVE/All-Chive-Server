package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Content;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long>, ContentCustomRepository {
    List<Content> findAllByIdIn(List<Long> contentIdList);

    void deleteAllByArchivingIdIn(List<Long> archivingId);
}
