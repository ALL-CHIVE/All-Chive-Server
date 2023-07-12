package allchive.server.api.tag.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UpdateTagRequest {
    @Schema(description = "태그 이름")
    private String name;
}
