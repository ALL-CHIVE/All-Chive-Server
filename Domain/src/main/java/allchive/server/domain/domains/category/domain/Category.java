package allchive.server.domain.domains.category.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.category.domain.enums.Topic;
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

    @ElementCollection
    @CollectionTable(name = "tbl_category_pin", joinColumns = @JoinColumn(name = "category_id"))
    private List<Long> pinUserId = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Topic topic;

    @Builder
    private Category(
            Long userId, String imageUrl, String title, boolean publicStatus, Topic topic) {
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.publicStatus = publicStatus;
        this.topic = topic;
        this.scrapCnt = 0L;
        this.linkCnt = 0L;
        this.imgCnt = 0L;
    }

    public static Category of(
            Long userId, String title, String imageUrl, boolean publicStatus, Topic topic) {
        return Category.builder()
                .userId(userId)
                .imageUrl(imageUrl)
                .title(title)
                .publicStatus(publicStatus)
                .topic(topic)
                .build();
    }

    public void update(String title, String imageUrl, boolean publicStatus, Topic topic) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.publicStatus = publicStatus;
        this.topic = topic;
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
}
