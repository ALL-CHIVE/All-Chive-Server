package allchive.server.api.auth.service.helper;

import static allchive.server.core.consts.AllchiveConst.APPLE_OAUTH_QUERY_STRING;

import allchive.server.core.annotation.Helper;
import allchive.server.core.dto.OIDCDecodePayload;
import allchive.server.core.error.exception.NoAppleCodeException;
import allchive.server.core.properties.AppleOAuthProperties;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import allchive.server.infrastructure.oauth.apple.client.AppleOAuthClient;
import allchive.server.infrastructure.oauth.apple.client.AppleOIDCClient;
import allchive.server.infrastructure.oauth.apple.helper.AppleLoginUtil;
import allchive.server.infrastructure.oauth.apple.response.AppleTokenResponse;
import allchive.server.infrastructure.oauth.kakao.dto.OIDCPublicKeysResponse;
import lombok.RequiredArgsConstructor;

@Helper
@RequiredArgsConstructor
public class AppleOauthHelper {
    private final AppleOAuthProperties appleOAuthProperties;
    private final AppleOAuthClient appleOAuthClient;
    private final AppleOIDCClient appleOIDCClient;
    private final OauthOIDCHelper oAuthOIDCHelper;

    /** Link * */
    public String getAppleOAuthLink(String referer) {
        return appleOAuthProperties.getBaseUrl()
                + String.format(
                        APPLE_OAUTH_QUERY_STRING,
                        appleOAuthProperties.getClientId(),
                        referer + appleOAuthProperties.getWebCallbackUrl());
    }

    public String getAppleOauthLinkDev() {
        return appleOAuthProperties.getBaseUrl()
                + String.format(
                        APPLE_OAUTH_QUERY_STRING,
                        appleOAuthProperties.getWebClientId(),
                        appleOAuthProperties.getRedirectUrl());
    }

    /** token * */
    public AppleTokenResponse getAppleOAuthToken(String code, String referer) {
        return appleOAuthClient.appleAuth(
                appleOAuthProperties.getClientId(),
                referer + appleOAuthProperties.getWebCallbackUrl(),
                code,
                this.getClientSecret());
    }

    public AppleTokenResponse getAppleOAuthTokenDev(String code) {
        return appleOAuthClient.appleAuth(
                appleOAuthProperties.getWebClientId(),
                appleOAuthProperties.getRedirectUrl(),
                code,
                this.getClientSecretDev());
    }

    /** idtoken 분석 * */
    public OauthInfo getAppleOAuthInfoByIdToken(String idToken) {
        OIDCDecodePayload oidcDecodePayload = this.getOIDCDecodePayload(idToken);
        return OauthInfo.builder()
                .provider(OauthProvider.APPLE)
                .oid(oidcDecodePayload.getSub())
                .build();
    }

    public OauthInfo getAppleOAuthInfoByIdTokenDev(String idToken) {
        OIDCDecodePayload oidcDecodePayload = this.getOIDCDecodePayloadDev(idToken);
        return OauthInfo.builder()
                .provider(OauthProvider.APPLE)
                .oid(oidcDecodePayload.getSub())
                .build();
    }

    /** oidc decode * */
    public OIDCDecodePayload getOIDCDecodePayload(String token) {
        OIDCPublicKeysResponse oidcPublicKeysResponse = appleOIDCClient.getAppleOIDCOpenKeys();
        return oAuthOIDCHelper.getPayloadFromIdToken(
                token,
                appleOAuthProperties.getBaseUrl(),
                appleOAuthProperties.getClientId(),
                oidcPublicKeysResponse);
    }

    public OIDCDecodePayload getOIDCDecodePayloadDev(String token) {
        OIDCPublicKeysResponse oidcPublicKeysResponse = appleOIDCClient.getAppleOIDCOpenKeys();
        return oAuthOIDCHelper.getPayloadFromIdToken(
                token,
                appleOAuthProperties.getBaseUrl(),
                appleOAuthProperties.getWebClientId(),
                oidcPublicKeysResponse);
    }

    /** apple측 회원 탈퇴 * */
    public void withdrawAppleOauthUser(String code, String referer) {
        if (code == null) {
            throw NoAppleCodeException.EXCEPTION;
        }
        AppleTokenResponse appleTokenResponse = getAppleOAuthToken(code, referer);
        appleOAuthClient.revoke(
                appleOAuthProperties.getClientId(),
                appleTokenResponse.getAccessToken(),
                this.getClientSecret());
    }

    public void withdrawAppleOauthUserDev(String code) {
        if (code == null) {
            throw NoAppleCodeException.EXCEPTION;
        }
        AppleTokenResponse appleTokenResponse = getAppleOAuthTokenDev(code);
        appleOAuthClient.revoke(
                appleOAuthProperties.getWebClientId(),
                appleTokenResponse.getAccessToken(),
                this.getClientSecret());
    }

    /** client secret 가져오기 * */
    private String getClientSecret() {
        return AppleLoginUtil.createClientSecret(
                appleOAuthProperties.getTeamId(),
                appleOAuthProperties.getClientId(),
                appleOAuthProperties.getKeyId(),
                appleOAuthProperties.getAuthKey(),
                appleOAuthProperties.getBaseUrl());
    }

    private String getClientSecretDev() {
        return AppleLoginUtil.createClientSecret(
                appleOAuthProperties.getTeamId(),
                appleOAuthProperties.getWebClientId(),
                appleOAuthProperties.getKeyId(),
                appleOAuthProperties.getAuthKey(),
                appleOAuthProperties.getBaseUrl());
    }
}
