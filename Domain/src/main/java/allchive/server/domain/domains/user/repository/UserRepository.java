package allchive.server.domain.domains.user.repository;


import allchive.server.domain.domains.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
