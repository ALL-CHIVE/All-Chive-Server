package allchive.server.domain.domains.user.repository;


import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauthInfo(OauthInfo oauthInfo);

    boolean existsByOauthInfo(OauthInfo oauthInfo);

    boolean existsByNickname(String nickname);

    List<User> findAllByIdIn(List<Long> userIds);
}
