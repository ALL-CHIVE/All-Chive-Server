package allchive.server.domain.domains.user.domain;

import allchive.server.domain.common.model.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name = "tbl_scrap")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scrap extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
