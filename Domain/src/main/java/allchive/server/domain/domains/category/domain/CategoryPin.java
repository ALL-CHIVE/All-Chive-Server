package allchive.server.domain.domains.category.domain;

import allchive.server.domain.common.model.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Table(name = "tbl_category_pin")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryPin extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private Long userId;
    private LocalDateTime pinnedAt;
}
