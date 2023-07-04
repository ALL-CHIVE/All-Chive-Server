package allchive.server.domain.domains.content.repository;


import allchive.server.domain.domains.content.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long>, ContentCustomRepository {
}
