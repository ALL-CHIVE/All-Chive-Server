package allchive.server.domain.domains.category.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import javax.persistence.*;

import allchive.server.domain.domains.category.domain.enums.Topic;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_category")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 카테고리 만든 사람
    private Long userId;
    private String title;
    private boolean publicStatus = Boolean.FALSE;
    private Topic topic;
}
