package allchive.server.api.search.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class DeleteLatestSearchRequest {
    @Schema(description = "삭제할 최근 검색어 고유번호 리스트")
    private List<Long> ids;
}
