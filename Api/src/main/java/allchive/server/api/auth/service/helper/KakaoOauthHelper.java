package allchive.server.api.auth.service.helper;

import static allchive.server.core.consts.AllchiveConst.KAKAO_OAUTH_QUERY_STRING;

import allchive.server.core.annotation.Helper;
import allchive.server.core.dto.OIDCDecodePayload;
import allchive.server.core.properties.KakaoOAuthProperties;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import allchive.server.infrastructure.oauth.kakao.client.KakaoInfoClient;
import allchive.server.infrastructure.oauth.kakao.client.KakaoOauthClient;
import allchive.server.infrastructure.oauth.kakao.dto.KakaoTokenResponse;
import allchive.server.infrastructure.oauth.kakao.dto.KakaoUnlinkTarget;
import allchive.server.infrastructure.oauth.kakao.dto.OIDCPublicKeysResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Helper
@Slf4j
@RequiredArgsConstructor
public class KakaoOauthHelper {
    private final KakaoOAuthProperties kakaoOauthProperties;
    private final KakaoInfoClient kakaoInfoClient;
    private final KakaoOauthClient kakaoOauthClient;
    private final OauthOIDCHelper oauthOIDCHelper;

    /** link * */
    public String getKaKaoOauthLink(String referer) {
        // TODO : 프론트 콜백 URL 알아내면 바꾸기
        return kakaoOauthProperties.getBaseUrl()
                + String.format(
                        KAKAO_OAUTH_QUERY_STRING,
                        kakaoOauthProperties.getClientId(),
                        referer + "kakao/callback");
    }

    public String getKaKaoOauthLinkDev() {
        return kakaoOauthProperties.getBaseUrl()
                + String.format(
                        KAKAO_OAUTH_QUERY_STRING,
                        kakaoOauthProperties.getClientId(),
                        kakaoOauthProperties.getRedirectUrl());
    }

    /** token * */
    public KakaoTokenResponse getKakaoOauthToken(String code, String referer) {
        // TODO : 프론트 콜백 URL 알아내면 바꾸기
        return kakaoOauthClient.kakaoAuth(
                kakaoOauthProperties.getClientId(),
                referer + "kakao/callback",
                code,
                kakaoOauthProperties.getClientSecret());
    }

    public KakaoTokenResponse getKakaoOauthTokenDev(String code) {
        return kakaoOauthClient.kakaoAuth(
                kakaoOauthProperties.getClientId(),
                kakaoOauthProperties.getRedirectUrl(),
                code,
                kakaoOauthProperties.getClientSecret());
    }

    /** idtoken 분석 * */
    public OauthInfo getKakaoOauthInfoByIdToken(String idToken) {
        OIDCDecodePayload oidcDecodePayload = getOIDCDecodePayload(idToken);
        return OauthInfo.of(OauthProvider.KAKAO, oidcDecodePayload.getSub());
    }

    /** oidc decode * */
    public OIDCDecodePayload getOIDCDecodePayload(String token) {
        OIDCPublicKeysResponse oidcPublicKeysResponse = kakaoOauthClient.getKakaoOIDCOpenKeys();
        return oauthOIDCHelper.getPayloadFromIdToken(
                token,
                kakaoOauthProperties.getBaseUrl(),
                kakaoOauthProperties.getAppId(),
                oidcPublicKeysResponse);
    }

    /** kakao측 회원 탈퇴 * */
    public void withdrawKakaoOauthUser(String oid) {
        String kakaoAdminKey = kakaoOauthProperties.getAdminKey();
        KakaoUnlinkTarget unlinkKaKaoTarget = KakaoUnlinkTarget.from(oid);
        String header = "KakaoAK " + kakaoAdminKey;
        log.info(
                "{} {} {}",
                header,
                unlinkKaKaoTarget.getTargetIdType(),
                unlinkKaKaoTarget.getAud());
        kakaoInfoClient.unlinkUser(header, unlinkKaKaoTarget);
    }
}
