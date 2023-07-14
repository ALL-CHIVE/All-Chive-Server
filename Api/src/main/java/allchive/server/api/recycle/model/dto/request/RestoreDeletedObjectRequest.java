package allchive.server.api.recycle.model.dto.request;


import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class RestoreDeletedObjectRequest {
    @ArraySchema(schema = @Schema(description = "복구할 아카이빙 id", type = "int", defaultValue = "1"))
    private List<Long> archivingIds;

    @ArraySchema(schema = @Schema(description = "복구할 컨텐츠 id", type = "int", defaultValue = "1"))
    private List<Long> contentIds;
}
