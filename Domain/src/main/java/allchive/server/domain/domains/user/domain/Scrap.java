package allchive.server.domain.domains.user.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_scrap")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scrap extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Long archivingId;

    @Builder
    private Scrap(User user, Long archivingId) {
        this.user = user;
        this.archivingId = archivingId;
    }

    public static Scrap of(User user, Long archivingId) {
        return Scrap.builder().user(user).archivingId(archivingId).build();
    }
}
