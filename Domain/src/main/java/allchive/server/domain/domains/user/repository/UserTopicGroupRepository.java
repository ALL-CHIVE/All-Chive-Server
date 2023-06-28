package allchive.server.domain.domains.user.repository;

import allchive.server.domain.domains.user.domain.UserTopicGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTopicGroupRepository extends JpaRepository<UserTopicGroup, Long> {
}
