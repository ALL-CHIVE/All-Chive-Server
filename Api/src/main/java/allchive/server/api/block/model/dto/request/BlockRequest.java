package allchive.server.api.block.model.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class BlockRequest {
    @Schema(defaultValue = "1", description = "차단할 유저의 id")
    private Long userId;
}
