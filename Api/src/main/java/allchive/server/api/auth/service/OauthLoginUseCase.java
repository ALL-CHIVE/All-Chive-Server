package allchive.server.api.auth.service;


import allchive.server.api.auth.model.dto.response.OauthSignInResponse;
import allchive.server.api.auth.model.dto.response.OauthTokenResponse;
import allchive.server.api.auth.service.helper.OauthHelper;
import allchive.server.api.auth.service.helper.TokenGenerateHelper;
import allchive.server.core.annotation.UseCase;
import allchive.server.core.helper.SpringEnvironmentHelper;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import allchive.server.domain.domains.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class OauthLoginUseCase {
    private final OauthHelper oauthHelper;
    private final UserDomainService userDomainService;
    private final TokenGenerateHelper tokenGenerateHelper;
    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Transactional
    public OauthSignInResponse loginWithCode(OauthProvider provider, String code, String referer) {
        final OauthTokenResponse oauthTokenResponse =
                oauthHelper.getCredential(provider, code, referer);
        return processLoginWithIdToken(provider, oauthTokenResponse.getIdToken());
    }

    @Transactional
    public OauthSignInResponse loginWithIdToken(OauthProvider provider, String idToken) {
        return processLoginWithIdToken(provider, idToken);
    }

    @Transactional
    public OauthSignInResponse devLogin(OauthProvider provider, String code) {
        final OauthTokenResponse oauthTokenResponse = oauthHelper.getCredentialDev(provider, code);
        if (!springEnvironmentHelper.isProdProfile()) {
            log.info("{}", oauthTokenResponse.getAccessToken());
        }
        return processLoginWithIdTokenDev(provider, oauthTokenResponse.getIdToken());
    }

    private OauthSignInResponse processLoginWithIdToken(OauthProvider provider, String idToken) {
        final OauthInfo oauthInfo = oauthHelper.getOauthInfo(provider, idToken);
        return checkUserCanLogin(oauthInfo, idToken);
    }

    private OauthSignInResponse processLoginWithIdTokenDev(OauthProvider provider, String idToken) {
        final OauthInfo oauthInfo = oauthHelper.getOauthInfoDev(provider, idToken);
        return checkUserCanLogin(oauthInfo, idToken);
    }

    private OauthSignInResponse checkUserCanLogin(OauthInfo oauthInfo, String idToken) {
        if (userDomainService.checkUserCanLogin(oauthInfo)) {
            User user = userDomainService.loginUser(oauthInfo);
            return tokenGenerateHelper.execute(user);
        } else {
            return OauthSignInResponse.cannotLogin(idToken);
        }
    }
}
