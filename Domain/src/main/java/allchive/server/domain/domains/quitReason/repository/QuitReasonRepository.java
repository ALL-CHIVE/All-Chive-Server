package allchive.server.domain.domains.quitReason.repository;

import allchive.server.domain.domains.quitReason.domain.QuitReason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuitReasonRepository extends JpaRepository<QuitReason, Long> {
}
