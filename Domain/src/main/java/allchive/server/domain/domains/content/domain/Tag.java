package allchive.server.domain.domains.content.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Table(name = "tbl_tag")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long userId;
    private LocalDateTime usedAt;

    @Builder
    private Tag(String name, Long userId, LocalDateTime usedAt) {
        this.name = name;
        this.userId = userId;
        this.usedAt = usedAt;
    }

    @Builder
    public static Tag of(String name, Long userId) {
        return Tag.builder()
                .name(name)
                .userId(userId)
                .usedAt(LocalDateTime.of(2000,1,1,0,0,0))
                .build();
    }
}
