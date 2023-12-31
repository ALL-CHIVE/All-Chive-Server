package allchive.server.domain.domains.recycle.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.recycle.domain.enums.RecycleType;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_recycle")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recycle extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RecycleType recycleType;

    private Long contentId;
    private Long archivingId;
    private Long userId;
    private LocalDateTime deletedAt;

    @Builder
    private Recycle(
            RecycleType recycleType,
            Long contentId,
            Long archivingId,
            Long userId,
            LocalDateTime deletedAt) {
        this.recycleType = recycleType;
        this.contentId = contentId;
        this.archivingId = archivingId;
        this.userId = userId;
        this.deletedAt = deletedAt;
    }

    public static Recycle of(
            RecycleType recycleType, Long contentId, Long archivingId, Long userId) {
        return Recycle.builder()
                .archivingId(archivingId)
                .contentId(contentId)
                .recycleType(recycleType)
                .userId(userId)
                .deletedAt(LocalDateTime.now())
                .build();
    }
}
