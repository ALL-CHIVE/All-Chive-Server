package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.ContentTagGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentTagGroupRepository
        extends JpaRepository<ContentTagGroup, Long>, ContentTagGroupCustomRepository {}
