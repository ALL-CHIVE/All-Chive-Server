package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import java.util.List;

import allchive.server.domain.domains.content.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContentTagGroupRepository
        extends JpaRepository<ContentTagGroup, Long>, ContentTagGroupCustomRepository {
    List<ContentTagGroup> findAllByContent(Content content);

    void deleteByTag(Tag tag);
}
