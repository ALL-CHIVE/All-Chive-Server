package allchive.server.api.auth.service.helper;


import allchive.server.api.auth.model.dto.OauthUserInfoDto;
import allchive.server.api.auth.model.dto.response.OauthLoginLinkResponse;
import allchive.server.api.auth.model.dto.response.OauthTokenResponse;
import allchive.server.core.annotation.Helper;
import allchive.server.core.error.exception.InvalidOauthProviderException;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import lombok.RequiredArgsConstructor;

@Helper
@RequiredArgsConstructor
public class OauthHelper {
    private final KakaoOauthHelper kakaoOauthHelper;
    private final AppleOauthHelper appleOauthHelper;

    /** oauth link 가져오기 * */
    public OauthLoginLinkResponse getOauthLinkDev(OauthProvider provider) {
        switch (provider) {
            case KAKAO:
                return new OauthLoginLinkResponse(kakaoOauthHelper.getKaKaoOauthLinkDev());
            case APPLE:
                return new OauthLoginLinkResponse(appleOauthHelper.getAppleOauthLinkDev());
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    public OauthLoginLinkResponse getOauthLink(OauthProvider provider, String referer) {
        switch (provider) {
            case KAKAO:
                return new OauthLoginLinkResponse(kakaoOauthHelper.getKaKaoOauthLink(referer));
            case APPLE:
                return new OauthLoginLinkResponse(appleOauthHelper.getAppleOAuthLink(referer));
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    /** idtoken 가져오기 * */
    public OauthTokenResponse getCredential(OauthProvider provider, String code, String referer) {
        switch (provider) {
            case KAKAO:
                return OauthTokenResponse.from(kakaoOauthHelper.getKakaoOauthToken(code, referer));
            case APPLE:
                return OauthTokenResponse.from(appleOauthHelper.getAppleOAuthToken(code, referer));
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    public OauthTokenResponse getCredentialDev(OauthProvider provider, String code) {
        switch (provider) {
            case KAKAO:
                return OauthTokenResponse.from(kakaoOauthHelper.getKakaoOauthTokenDev(code));
            case APPLE:
                return OauthTokenResponse.from(appleOauthHelper.getAppleOAuthTokenDev(code));
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    /** idtoken 분석 * */
    public OauthInfo getOauthInfo(OauthProvider provider, String idToken) {
        switch (provider) {
            case KAKAO:
                return kakaoOauthHelper.getKakaoOauthInfoByIdToken(idToken);
            case APPLE:
                return appleOauthHelper.getAppleOAuthInfoByIdToken(idToken);
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    public OauthInfo getOauthInfoDev(OauthProvider provider, String idToken) {
        switch (provider) {
            case KAKAO:
                return kakaoOauthHelper.getKakaoOauthInfoByIdTokenDev(idToken);
            case APPLE:
                return appleOauthHelper.getAppleOAuthInfoByIdTokenDev(idToken);
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    /** 회원탈퇴 * */
    public void withdraw(OauthProvider provider, String oid, String appleAccessToken) {
        switch (provider) {
            case KAKAO:
                kakaoOauthHelper.withdrawKakaoOauthUser(oid);
                break;
            case APPLE:
                appleOauthHelper.withdrawAppleOauthUser(appleAccessToken);
                break;
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    /** 유저 정보 가져오기 * */
    public OauthUserInfoDto getUserInfo(OauthProvider provider, String oauthAccessToken) {
        switch (provider) {
            case KAKAO:
                return kakaoOauthHelper.getUserInfo(oauthAccessToken);
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }
}
