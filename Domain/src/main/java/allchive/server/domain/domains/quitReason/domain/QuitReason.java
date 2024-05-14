package allchive.server.domain.domains.quitReason.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.quitReason.domain.enums.Reason;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_quit_reason")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuitReason extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull private Long userId;

    private Reason reason;

    @Builder
    private QuitReason(Long userId, Reason reason) {
        this.userId = userId;
        this.reason = reason;
    }

    public static QuitReason of(Long userId, Reason reason) {
        return QuitReason.builder().userId(userId).reason(reason).build();
    }
}
