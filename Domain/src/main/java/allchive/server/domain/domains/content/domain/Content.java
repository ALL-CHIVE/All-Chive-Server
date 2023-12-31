package allchive.server.domain.domains.content.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.content.domain.enums.ContentType;
import allchive.server.domain.domains.content.exception.exceptions.AlreadyDeletedContentException;
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

    private Long archivingId;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String linkUrl;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String memo;

    private boolean deleteStatus = Boolean.FALSE;

    @Builder
    private Content(
            Long archivingId,
            ContentType contentType,
            String imageUrl,
            String linkUrl,
            String title,
            String memo,
            boolean deleteStatus) {
        this.archivingId = archivingId;
        this.contentType = contentType;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
        this.title = title;
        this.memo = memo;
        this.deleteStatus = deleteStatus;
    }

    public static Content of(
            Long archivingId,
            ContentType contentType,
            String imageUrl,
            String linkUrl,
            String title,
            String memo) {
        return Content.builder()
                .archivingId(archivingId)
                .contentType(contentType)
                .imageUrl(imageUrl)
                .linkUrl(linkUrl)
                .title(title)
                .memo(memo)
                .deleteStatus(Boolean.FALSE)
                .build();
    }

    public void delete() {
        this.deleteStatus = Boolean.TRUE;
    }

    public void restore() {
        this.deleteStatus = Boolean.FALSE;
    }

    public void updateContent(
            Long archivingId, String imgUrl, String link, String title, String memo) {
        this.archivingId = archivingId;
        this.imageUrl = imgUrl;
        this.linkUrl = link;
        this.title = title;
        this.memo = memo;
    }

    public void validateNotDelete() {
        if (this.isDeleteStatus()) {
            throw AlreadyDeletedContentException.EXCEPTION;
        }
    }
}
