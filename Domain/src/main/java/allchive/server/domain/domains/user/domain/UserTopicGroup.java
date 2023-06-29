package allchive.server.domain.domains.user.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_user_topic_group")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTopicGroup extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
