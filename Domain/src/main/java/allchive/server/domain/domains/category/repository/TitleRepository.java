package allchive.server.domain.domains.category.repository;


import allchive.server.domain.domains.category.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Topic, Long> {}
