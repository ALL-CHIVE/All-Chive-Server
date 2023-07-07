package allchive.server.api.user.model.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CheckUserNicknameRequest {
    @Schema(defaultValue = "닉네임", description = "닉네임")
    private String nickname;
}
