package allchive.server.api.auth.model.dto.response;


import allchive.server.infrastructure.oauth.kakao.dto.KakaoTokenResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String idToken;

    public static OauthTokenResponse from(KakaoTokenResponse kakaoTokenResponse) {
        return OauthTokenResponse.builder()
                .idToken(kakaoTokenResponse.getIdToken())
                .refreshToken(kakaoTokenResponse.getRefreshToken())
                .accessToken(kakaoTokenResponse.getAccessToken())
                .build();
    }
}
