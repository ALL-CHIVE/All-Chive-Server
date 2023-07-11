package allchive.server.api.auth.service.helper;
import allchive.server.core.properties.AppleOAuthProperties;
//import allchive.server.infrastructure.oauth.apple.client.AppleOAuthClient;
//import allchive.server.infrastructure.oauth.apple.client.AppleOIDCClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static allchive.server.core.consts.AllchiveConst.APPLE_OAUTH_QUERY_STRING;


@Component
@RequiredArgsConstructor
public class AppleOAuthHelper {
    private final AppleOAuthProperties appleOAuthProperties;
//    private final AppleOAuthClient appleOAuthClient;
//    private final AppleOIDCClient appleOIDCClient;
//    private final OauthOIDCHelper oAuthOIDCHelper;

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
                        appleOAuthProperties.getClientId(),
                        appleOAuthProperties.getRedirectUrl());
    }

//    public AppleTokenResponse getOAuthTokenTest(String code) {
//        return appleOAuthClient.appleAuth(
//                appleOAuthProperties.getClientId(),
//                appleOAuthProperties.getRedirectUrl(),
//                code,
//                this.getClientSecret());
//    }
//
//    public OAuthUserInfoDto getUserInfo(String idToken, AppType type) {
//        return OAuthUserInfoDto.builder()
//                .oAuthProvider(Provider.APPLE)
//                .email(getOIDCDecodePayload(idToken, type).getEmail())
//                .oauthId(getOIDCDecodePayload(idToken, type).getSub())
//                .build();
//    }
//
//
//    public AppleTokenResponse getOAuthToken(String code, String referer) {
//        return appleOAuthClient.appleAuth(
//                appleOAuthProperties.getClientId(),
//                referer + appleOAuthProperties.getWebCallbackUrl(),
//                code,
//                this.getClientSecret());
//    }
//
//    public AppleRevokeResponse revoke(String appleOAuthAccessToken) {
//        appleOAuthClient.revoke(
//                appleOAuthProperties.getClientId(), appleOAuthAccessToken, this.getClientSecret());
//        return new AppleRevokeResponse(true);
//    }
//
//    public OAuthInfo getOAuthInfoByIdToken(String idToken, AppType type) {
//        OIDCDecodePayload oidcDecodePayload = this.getOIDCDecodePayload(idToken, type);
//        return OAuthInfo.builder().provider(Provider.APPLE).oid(oidcDecodePayload.getSub()).build();
//    }
//
//    public OAuthIdTokenDto getOAuthUserInfoByIdToken(String idToken, AppType type) {
//        OIDCDecodePayload oidcDecodePayload = this.getOIDCDecodePayload(idToken, type);
//        return OAuthIdTokenDto.builder()
//                .provider(Provider.APPLE)
//                .email(oidcDecodePayload.getEmail())
//                .oid(oidcDecodePayload.getSub())
//                .build();
//    }
//
//    public OIDCDecodePayload getOIDCDecodePayload(String token, AppType type) {
//        OIDCPublicKeysResponse oidcPublicKeysResponse = appleOIDCClient.getAppleOIDCOpenKeys();
//        return oAuthOIDCHelper.getPayloadFromIdToken(
//                token,
//                appleOAuthProperties.getBaseUrl(),
//                this.getClientIdByAppType(type),
//                oidcPublicKeysResponse);
//    }
//
//    private String getClientSecret() {
//        return AppleLoginUtil.createClientSecret(
//                appleOAuthProperties.getTeamId(),
//                appleOAuthProperties.getClientId(),
//                appleOAuthProperties.getKeyId(),
//                appleOAuthProperties.getKeyPath(),
//                appleOAuthProperties.getBaseUrl());
//    }
//
//    private String getClientIdByAppType(AppType type) {
//        switch (type) {
//            case APP:
//                return appleOAuthProperties.getAppClientId();
//            case WEB:
//                return appleOAuthProperties.getClientId();
//            default:
//                throw new BaseException(INVALID_OAUTH_APP_TYPE);
//        }
//    }
}
