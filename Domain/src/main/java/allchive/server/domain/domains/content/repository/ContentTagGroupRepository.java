package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.content.domain.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentTagGroupRepository
        extends JpaRepository<ContentTagGroup, Long>, ContentTagGroupCustomRepository {
    List<ContentTagGroup> findAllByContent(Content content);

    void deleteByTag(Tag tag);

    void deleteAllByContentIn(List<Content> contents);
}
