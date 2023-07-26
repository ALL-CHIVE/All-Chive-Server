package allchive.server.api.search.model.dto.response;


import allchive.server.api.search.model.vo.LatestSearchVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SearchVoListResponse {
    private List<LatestSearchVo> keywords;

    @Builder
    private SearchVoListResponse(List<LatestSearchVo> keywords) {
        this.keywords = keywords;
    }

    public static SearchVoListResponse from(List<LatestSearchVo> keywords) {
        return SearchVoListResponse.builder().keywords(keywords).build();
    }
}
