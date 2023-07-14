package allchive.server.api.recycle.model.dto.request;


import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class ClearDeletedObjectRequest {
    @ArraySchema(schema = @Schema(description = "영구 삭제할 아카이빙 id", type = "int", defaultValue = "1"))
    private List<Long> archivingIds;

    @ArraySchema(schema = @Schema(description = "영구 삭제할 컨텐츠 id", type = "int", defaultValue = "1"))
    private List<Long> contentIds;
}
