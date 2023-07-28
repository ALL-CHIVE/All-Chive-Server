package allchive.server.api.auth.model.dto;


import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import allchive.server.infrastructure.oauth.kakao.dto.KakaoInformationResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OauthUserInfoDto {
    private String email;
    private String name;
    private OauthProvider oauthProvider;

    @Builder
    private OauthUserInfoDto(String email, String name, OauthProvider oauthProvider) {
        this.email = email;
        this.name = name;
        this.oauthProvider = oauthProvider;
    }

    public static OauthUserInfoDto fromKakao(KakaoInformationResponse response) {
        return OauthUserInfoDto.builder()
                .email(response.getEmail())
                .name(response.getNickName())
                .oauthProvider(OauthProvider.KAKAO)
                .build();
    }
}
;
