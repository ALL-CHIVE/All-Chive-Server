package allchive.server.api.auth.service;


import allchive.server.api.auth.model.dto.response.OauthSignInResponse;
import allchive.server.api.auth.model.dto.response.OauthTokenResponse;
import allchive.server.api.auth.service.helper.KakaoOauthHelper;
import allchive.server.api.auth.service.helper.TokenGenerateHelper;
import allchive.server.core.annotation.UseCase;
import allchive.server.core.error.exception.InvalidOauthProviderException;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import allchive.server.domain.domains.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class OauthLoginUseCase {
    private final KakaoOauthHelper kakaoOauthHelper;
    private final UserDomainService userDomainService;
    private final TokenGenerateHelper tokenGenerateHelper;

    public OauthSignInResponse loginWithCode(OauthProvider provider, String code, String referer) {
        final OauthTokenResponse oauthTokenResponse = getCredential(provider, code, referer);
        return processLoginWithIdToken(provider, oauthTokenResponse.getIdToken());
    }

    public OauthSignInResponse loginWithIdToken(OauthProvider provider, String idToken) {
        return processLoginWithIdToken(provider, idToken);
    }

    public OauthSignInResponse devLogin(OauthProvider provider, String code) {
        final OauthTokenResponse oauthTokenResponse = getCredentialDev(provider, code);
        return processLoginWithIdToken(provider, oauthTokenResponse.getIdToken());
    }

    private OauthSignInResponse processLoginWithIdToken(OauthProvider provider, String idToken) {
        final OauthInfo oauthInfo = getOauthInfo(provider, idToken);
        if (userDomainService.checkUserCanLogin(oauthInfo)) {
            User user = userDomainService.loginUser(oauthInfo);
            return tokenGenerateHelper.execute(user);
        } else {
            return OauthSignInResponse.cannotLogin(idToken);
        }
    }

    /** idtoken 가져오기 * */
    private OauthTokenResponse getCredential(OauthProvider provider, String code, String referer) {
        switch (provider) {
            case KAKAO:
                return OauthTokenResponse.from(kakaoOauthHelper.getKakaoOauthToken(code, referer));
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    private OauthTokenResponse getCredentialDev(OauthProvider provider, String code) {
        switch (provider) {
            case KAKAO:
                return OauthTokenResponse.from(kakaoOauthHelper.getKakaoOauthTokenDev(code));
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    /** idtoken 분석 * */
    private OauthInfo getOauthInfo(OauthProvider provider, String idToken) {
        switch (provider) {
            case KAKAO:
                return kakaoOauthHelper.getOauthInfoByIdToken(idToken);
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }
}
