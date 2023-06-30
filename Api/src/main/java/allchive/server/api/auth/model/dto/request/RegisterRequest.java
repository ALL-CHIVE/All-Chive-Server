package allchive.server.api.auth.model.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    @Schema(defaultValue = "www.s3....", description = "프로필 이미지 url")
    @NotBlank(message = "프로필 이미지 url을 입력하세요")
    private String profileImgUrl;

    @Schema(defaultValue = "닉네임", description = "닉네임")
    @NotBlank(message = "닉네임을 입력하세요")
    private String nickname;
}
