package allchive.server.api.auth.service;


import allchive.server.api.auth.model.dto.response.OauthSignInResponse;
import allchive.server.api.auth.model.dto.response.OauthTokenResponse;
import allchive.server.api.auth.service.helper.OauthHelper;
import allchive.server.api.auth.service.helper.TokenGenerateHelper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import allchive.server.domain.domains.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class OauthLoginUseCase {
    private final OauthHelper oauthHelper;
    private final UserDomainService userDomainService;
    private final TokenGenerateHelper tokenGenerateHelper;

    public OauthSignInResponse loginWithCode(OauthProvider provider, String code, String referer) {
        final OauthTokenResponse oauthTokenResponse =
                oauthHelper.getCredential(provider, code, referer);
        return processLoginWithIdToken(provider, oauthTokenResponse.getIdToken());
    }

    public OauthSignInResponse loginWithIdToken(OauthProvider provider, String idToken) {
        return processLoginWithIdToken(provider, idToken);
    }

    public OauthSignInResponse devLogin(OauthProvider provider, String code) {
        final OauthTokenResponse oauthTokenResponse = oauthHelper.getCredentialDev(provider, code);
        return processLoginWithIdToken(provider, oauthTokenResponse.getIdToken());
    }

    private OauthSignInResponse processLoginWithIdToken(OauthProvider provider, String idToken) {
        final OauthInfo oauthInfo = oauthHelper.getOauthInfo(provider, idToken);
        if (userDomainService.checkUserCanLogin(oauthInfo)) {
            User user = userDomainService.loginUser(oauthInfo);
            return tokenGenerateHelper.execute(user);
        } else {
            return OauthSignInResponse.cannotLogin(idToken);
        }
    }
}
