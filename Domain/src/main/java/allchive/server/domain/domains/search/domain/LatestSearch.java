package allchive.server.domain.domains.search.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.search.exception.exceptions.NoAuthorityUpdateLatestSearchException;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 레디스로 전환 고려? * */
@Getter
@Table(name = "tbl_latest_search")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LatestSearch extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;
    private Long userId;

    @Builder
    private LatestSearch(String keyword, Long userId) {
        this.keyword = keyword;
        this.userId = userId;
    }

    public static LatestSearch of(String keyword, Long userId) {
        return LatestSearch.builder().keyword(keyword).userId(userId).build();
    }

    public void validateUser(Long userId) {
        if (!this.userId.equals(userId)) {
            throw NoAuthorityUpdateLatestSearchException.EXCEPTION;
        }
    }
}
