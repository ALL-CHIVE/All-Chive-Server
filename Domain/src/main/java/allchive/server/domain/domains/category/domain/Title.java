package allchive.server.domain.domains.category.domain;

import allchive.server.domain.common.model.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "tbl_category")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Title extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
