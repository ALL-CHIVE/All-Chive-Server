package allchive.server.api.block.model.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BlockResponse {
    @Schema(defaultValue = "이름", description = "차단한 유저의 닉네임")
    private String nickname;

    @Builder
    private BlockResponse(String nickname) {
        this.nickname = nickname;
    }

    public static BlockResponse from(String nickname) {
        return BlockResponse.builder().nickname(nickname).build();
    }
}
