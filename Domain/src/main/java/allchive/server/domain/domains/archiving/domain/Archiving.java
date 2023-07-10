package allchive.server.domain.domains.archiving.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import allchive.server.domain.domains.archiving.exception.exceptions.DeletedArchivingException;
import allchive.server.domain.domains.archiving.exception.exceptions.NoAuthurityUpdateArchivingException;
import allchive.server.domain.domains.archiving.exception.exceptions.NotPublicArchivingException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_archiving")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Archiving extends BaseTimeEntity {
    @Id
    @Column(name = "archiving_id", nullable = false, updatable = false)
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
    @CollectionTable(name = "tbl_archiving_pin", joinColumns = @JoinColumn(name = "archiving_id"))
    private List<Long> pinUserId = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    @Builder
    private Archiving(
            Long userId,
            String imageUrl,
            String title,
            boolean publicStatus,
            boolean deleteStatus,
            Category category) {
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.publicStatus = publicStatus;
        this.deleteStatus = deleteStatus;
        this.category = category;
        this.scrapCnt = 0L;
        this.linkCnt = 0L;
        this.imgCnt = 0L;
    }

    public static Archiving of(
            Long userId, String title, String imageUrl, boolean publicStatus, Category category) {
        return Archiving.builder()
                .userId(userId)
                .imageUrl(imageUrl)
                .title(title)
                .publicStatus(publicStatus)
                .deleteStatus(Boolean.FALSE)
                .category(category)
                .build();
    }

    public void update(String title, String imageUrl, boolean publicStatus, Category category) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.publicStatus = publicStatus;
        this.category = category;
    }

    public void validateUser(Long userId) {
        if (!this.userId.equals(userId)) {
            throw NoAuthurityUpdateArchivingException.EXCEPTION;
        }
    }

    public void validatePublicStatus(Long userId) {
        if (!this.publicStatus && !this.userId.equals(userId)) {
            throw NotPublicArchivingException.EXCEPTION;
        }
    }

    public void validateDeleteStatus(Long userId) {
        if (this.deleteStatus && !this.userId.equals(userId)) {
            throw DeletedArchivingException.EXCEPTION;
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
