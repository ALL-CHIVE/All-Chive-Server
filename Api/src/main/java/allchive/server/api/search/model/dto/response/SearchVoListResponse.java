package allchive.server.api.search.model.dto.response;


import allchive.server.api.search.model.vo.LatestSearchVo;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

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
