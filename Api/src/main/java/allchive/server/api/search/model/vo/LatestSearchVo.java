package allchive.server.api.search.model.vo;


import allchive.server.domain.domains.search.domain.LatestSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LatestSearchVo {
    @Schema(description = "최근 검색 내용")
    private String word;

    @Schema(description = "최근 검색 내용 고유번호")
    private Long latestSearchId;

    @Builder
    private LatestSearchVo(String word, Long latestSearchId) {
        this.word = word;
        this.latestSearchId = latestSearchId;
    }

    public static LatestSearchVo from(LatestSearch latestSearch) {
        return LatestSearchVo.builder()
                .word(latestSearch.getKeyword())
                .latestSearchId(latestSearch.getId())
                .build();
    }
}
