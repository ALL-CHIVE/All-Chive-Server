package allchive.server.domain.domains.content.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.content.exception.exceptions.NoAuthorityUpdateTagException;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        return Tag.builder().name(name).userId(userId).usedAt(null).build();
    }

    public void validateUser(Long userId) {
        if (!this.userId.equals(userId)) {
            throw NoAuthorityUpdateTagException.EXCEPTION;
        }
    }

    public void updateName(String name) {
        this.name = name;
    }
}
