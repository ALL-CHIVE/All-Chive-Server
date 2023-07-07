package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentTagGroupRepository
        extends JpaRepository<ContentTagGroup, Long>, ContentTagGroupCustomRepository {
    List<ContentTagGroup> findAllByContent(Content content);
}
