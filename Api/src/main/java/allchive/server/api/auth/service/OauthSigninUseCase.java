package allchive.server.api.auth.service;


import allchive.server.api.auth.model.dto.response.OauthTokenResponse;
import allchive.server.api.auth.model.dto.response.SignInResponse;
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
public class OauthSigninUseCase {

    private final KakaoOauthHelper kakaoOauthHelper;
    private final UserDomainService userDomainService;
    private final TokenGenerateHelper tokenGenerateHelper;

    public SignInResponse execute(OauthProvider provider, String code, String referer) {
        final OauthTokenResponse oauthTokenResponse = getCredential(provider, code, referer);
        final OauthInfo oauthInfo = getOauthInfo(provider, oauthTokenResponse.getIdToken());
        if (userDomainService.checkUserCanLogin(oauthInfo)) {
            User user = userDomainService.loginUser(oauthInfo);
            return tokenGenerateHelper.execute(user);
        } else {
            return SignInResponse.cannotLogin();
        }
    }

    public SignInResponse executeTest(OauthProvider provider, String code) {
        final OauthTokenResponse oauthTokenResponse = getCredentialTest(provider, code);
        final OauthInfo oauthInfo = getOauthInfo(provider, oauthTokenResponse.getIdToken());
        if (userDomainService.checkUserCanLogin(oauthInfo)) {
            User user = userDomainService.loginUser(oauthInfo);
            return tokenGenerateHelper.execute(user);
        } else {
            return SignInResponse.cannotLogin();
        }
    }

    private OauthTokenResponse getCredential(OauthProvider provider, String code, String referer) {
        switch (provider) {
            case KAKAO:
                return OauthTokenResponse.from(kakaoOauthHelper.getKakaoOauthToken(code, referer));
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    private OauthTokenResponse getCredentialTest(OauthProvider provider, String code) {
        switch (provider) {
            case KAKAO:
                return OauthTokenResponse.from(kakaoOauthHelper.getKakaoOauthTokenTest(code));
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    private OauthInfo getOauthInfo(OauthProvider provider, String idToken) {
        switch (provider) {
            case KAKAO:
                return kakaoOauthHelper.getOauthInfoByIdToken(idToken);
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }
}
