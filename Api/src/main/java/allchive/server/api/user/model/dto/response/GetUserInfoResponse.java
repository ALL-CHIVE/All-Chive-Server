package allchive.server.api.user.model.dto.response;


import allchive.server.api.common.util.UrlUtil;
import allchive.server.domain.domains.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetUserInfoResponse {
    @Schema(defaultValue = "프로필 이미지 url", description = "프로필 이미지 url")
    private String imgUrl;

    @Schema(defaultValue = "asd@asd.com", description = "이메일")
    private String email;

    @Schema(defaultValue = "이름", description = "유저 이름")
    private String name;

    @Schema(defaultValue = "닉네임", description = "닉네임")
    private String nickname;

    @Builder
    public GetUserInfoResponse(String imgUrl, String email, String name, String nickname) {
        this.imgUrl = imgUrl;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }

    public static GetUserInfoResponse from(User user) {
        return GetUserInfoResponse.builder()
                .imgUrl(UrlUtil.toAssetUrl(user.getProfileImgUrl()))
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .build();
    }
}
