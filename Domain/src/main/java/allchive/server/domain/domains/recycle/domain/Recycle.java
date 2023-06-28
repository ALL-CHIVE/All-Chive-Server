package allchive.server.domain.domains.recycle.domain;

import allchive.server.domain.common.model.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
}
