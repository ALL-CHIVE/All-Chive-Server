package allchive.server.domain.domains.content.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    private Content(Long categoryId, ContentType contentType, String imageUrl,
                   String linkUrl, String title, String memo, boolean deleteStatus) {
        this.categoryId = categoryId;
        this.contentType = contentType;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
        this.title = title;
        this.memo = memo;
        this.deleteStatus = deleteStatus;
    }

    public static Content of(Long categoryId, ContentType contentType, String imageUrl,
                             String linkUrl, String title, String memo) {
        return Content.builder()
                .categoryId(categoryId)
                .contentType(contentType)
                .imageUrl(imageUrl)
                .linkUrl(linkUrl)
                .title(title)
                .memo(memo)
                .deleteStatus(Boolean.FALSE)
                .build();
    }
}
