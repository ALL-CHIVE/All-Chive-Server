package allchive.server.api.search.model.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SearchRequest {
    @Schema(defaultValue = "키워드", description = "검색 내용")
    private String keyword;
}
