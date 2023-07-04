package allchive.server.domain.domains.content.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import javax.persistence.*;

import allchive.server.domain.domains.content.domain.enums.ContentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_content")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    private String imageUrl;
    private String linkUrl;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String memo;

    private boolean deleteStatus = Boolean.FALSE;
}
