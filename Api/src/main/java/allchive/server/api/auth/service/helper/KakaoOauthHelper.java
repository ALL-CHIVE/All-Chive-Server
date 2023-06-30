package allchive.server.api.auth.service.helper;

import allchive.server.core.annotation.Helper;
import allchive.server.core.properties.KakaoOAuthProperties;
import allchive.server.infrastructure.oauth.kakao.client.KakaoInfoClient;
import allchive.server.infrastructure.oauth.kakao.client.KakaoOauthClient;
import lombok.RequiredArgsConstructor;

import static allchive.server.core.consts.AllchiveConst.KAKAO_OAUTH_QUERY_STRING;

@Helper
@RequiredArgsConstructor
public class KakaoOauthHelper {
    private final KakaoOAuthProperties kakaoOauthProperties;
    private final KakaoInfoClient kakaoInfoClient;
    private final KakaoOauthClient kakaoOauthClient;

//    private final OauthOIDCHelper oauthOIDCHelper;

    public String getKaKaoOauthLinkTest() {
        return kakaoOauthProperties.getBaseUrl()
                + String.format(
                        KAKAO_OAUTH_QUERY_STRING,
                        kakaoOauthProperties.getClientId(),
                        kakaoOauthProperties.getRedirectUrl());
    }

    public String getKaKaoOauthLink(String referer) {
        return kakaoOauthProperties.getBaseUrl()
                + String.format(
                        KAKAO_OAUTH_QUERY_STRING,
                        kakaoOauthProperties.getClientId(),
                        referer + "/kakao/callback");
    }

//    public KakaoTokenResponse getOauthToken(String code, String referer) {
//
//        return kakaoOauthClient.kakaoAuth(
//                kakaoOauthProperties.getKakaoClientId(),
//                referer + "/kakao/callback",
//                code,
//                kakaoOauthProperties.getKakaoClientSecret());
//    }

//    public KakaoTokenResponse getOauthTokenTest(String code) {
//
//        return kakaoOauthClient.kakaoAuth(
//                kakaoOauthProperties.getKakaoClientId(),
//                kakaoOauthProperties.getRedirectUrl(),
//                code,
//                kakaoOauthProperties.getKakaoClientSecret());
//    }
//
//    public OIDCDecodePayload getOIDCDecodePayload(String token) {
//        OIDCPublicKeysResponse oidcPublicKeysResponse = kakaoOauthClient.getKakaoOIDCOpenKeys();
//        return oauthOIDCHelper.getPayloadFromIdToken(
//                token,
//                kakaoOauthProperties.getKakaoBaseUrl(),
//                kakaoOauthProperties.getKakaoAppId(),
//                oidcPublicKeysResponse);
//    }
}
