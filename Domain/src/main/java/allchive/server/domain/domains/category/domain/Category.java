package allchive.server.domain.domains.category.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.category.domain.enums.Subject;
import allchive.server.domain.domains.category.exception.exceptions.DeletedCategoryException;
import allchive.server.domain.domains.category.exception.exceptions.NoAuthurityUpdateCategoryException;
import allchive.server.domain.domains.category.exception.exceptions.NotPublicCategoryException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_category")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {
    @Id
    @Column(name = "category_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 카테고리 만든 사람
    private Long userId;
    private String imageUrl;
    private String title;
    private Boolean publicStatus = Boolean.FALSE;
    private Long scrapCnt;
    private Long linkCnt;
    private Long imgCnt;
    private Boolean deleteStatus = Boolean.FALSE;

    @ElementCollection
    @CollectionTable(name = "tbl_category_pin", joinColumns = @JoinColumn(name = "category_id"))
    private List<Long> pinUserId = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Subject subject;

    @Builder
    private Category(
            Long userId,
            String imageUrl,
            String title,
            boolean publicStatus,
            boolean deleteStatus,
            Subject subject) {
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.publicStatus = publicStatus;
        this.deleteStatus = deleteStatus;
        this.subject = subject;
        this.scrapCnt = 0L;
        this.linkCnt = 0L;
        this.imgCnt = 0L;
    }

    public static Category of(
            Long userId, String title, String imageUrl, boolean publicStatus, Subject subject) {
        return Category.builder()
                .userId(userId)
                .imageUrl(imageUrl)
                .title(title)
                .publicStatus(publicStatus)
                .deleteStatus(Boolean.FALSE)
                .subject(subject)
                .build();
    }

    public void update(String title, String imageUrl, boolean publicStatus, Subject subject) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.publicStatus = publicStatus;
        this.subject = subject;
    }

    public void validateUser(Long userId) {
        if (!this.userId.equals(userId)) {
            throw NoAuthurityUpdateCategoryException.EXCEPTION;
        }
    }

    public void validatePublicStatus(Long userId) {
        if (!this.publicStatus && !this.userId.equals(userId)) {
            throw NotPublicCategoryException.EXCEPTION;
        }
    }

    public void validateDeleteStatus(Long userId) {
        if (this.deleteStatus && !this.userId.equals(userId)) {
            throw DeletedCategoryException.EXCEPTION;
        }
    }

    public void updateScrapCnt(int i) {
        this.scrapCnt += i;
    }

    public void addPinUserId(Long userId) {
        this.getPinUserId().add(userId);
    }

    public void deletePinUserId(Long userId) {
        this.getPinUserId().remove(userId);
    }

    public void delete() {
        this.deleteStatus = Boolean.TRUE;
    }
}
