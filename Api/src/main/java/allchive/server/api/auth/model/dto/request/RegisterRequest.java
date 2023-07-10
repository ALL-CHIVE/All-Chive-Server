package allchive.server.api.auth.model.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    @Schema(
            defaultValue = "staging/archiving/1/d241218a-a64c-4443-8aa4-ce98017a3d12",
            description = "프로필 이미지 url")
    @NotBlank(message = "프로필 이미지 key를 입력하세요")
    private String profileImgKey;

    @Schema(defaultValue = "닉네임", description = "닉네임")
    @NotBlank(message = "닉네임을 입력하세요")
    private String nickname;
}
