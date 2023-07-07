package allchive.server.domain.domains.recycle.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import javax.persistence.*;

import allchive.server.domain.domains.content.domain.enums.ContentType;
import allchive.server.domain.domains.recycle.domain.enums.RecycleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private Long categoryId;
    private Long userId;
    private LocalDateTime deletedAt;

    @Builder
    private Recycle(RecycleType recycleType, Long contentId, Long categoryId, Long userId, LocalDateTime deletedAt) {
        this.recycleType = recycleType;
        this.contentId = contentId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.deletedAt = deletedAt;
    }

    public static Recycle of(RecycleType recycleType, Long contentId, Long categoryId, Long userId) {
        return Recycle.builder()
                .categoryId(categoryId)
                .contentId(contentId)
                .recycleType(recycleType)
                .userId(userId)
                .deletedAt(LocalDateTime.now())
                .build();
    }

}
