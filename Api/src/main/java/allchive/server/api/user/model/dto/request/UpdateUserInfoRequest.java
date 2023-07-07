package allchive.server.api.user.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UpdateUserInfoRequest {
    @Schema(defaultValue = "https://asset.staging.allchive.co.kr.s3.ap-northeast-2.amazonaws.com/staging/user/1/e024eaf4-74a", description = "프로필 이미지 url")
    private String imgUrl;

    @Schema(defaultValue = "asd@asd.com", description = "이메일")
    private String email;

    @Schema(defaultValue = "이름", description = "유저 이름")
    private String name;

    @Schema(defaultValue = "닉네임", description = "닉네임")
    private String nickname;
}
