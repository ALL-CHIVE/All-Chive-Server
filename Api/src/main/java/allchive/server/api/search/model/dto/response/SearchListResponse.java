package allchive.server.api.search.model.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchListResponse {
    @Schema(description = "검색어 목록")
    private List<String> keyword;

    @Builder
    private SearchListResponse(List<String> keyword) {
        this.keyword = keyword;
    }

    public static SearchListResponse from(List<String> keywords) {
        return SearchListResponse.builder().keyword(keywords).build();
    }
}
