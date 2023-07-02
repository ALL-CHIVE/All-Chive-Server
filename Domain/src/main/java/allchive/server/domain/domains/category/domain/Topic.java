package allchive.server.domain.domains.category.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_topic")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Topic{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
}
