package allchive.server.api.search.model.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class DeleteLatestSearchRequest {
    @Schema(description = "삭제할 최근 검색어 고유번호 리스트")
    private List<Long> ids;
}
