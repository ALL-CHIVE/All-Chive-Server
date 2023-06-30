package allchive.server.api.auth.service;


import allchive.server.api.auth.model.dto.request.RegisterRequest;
import allchive.server.api.auth.model.dto.response.OauthRegisterResponse;
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
public class OauthRegisterUseCase {
    private final KakaoOauthHelper kakaoOauthHelper;
    private final UserDomainService userDomainService;
    private final TokenGenerateHelper tokenGenerateHelper;

    public OauthRegisterResponse execute(
            OauthProvider provider, String idToken, RegisterRequest registerRequest) {
        final OauthInfo oauthInfo = getOauthInfo(provider, idToken);
        final User user =
                userDomainService.registerUser(
                        registerRequest.getNickname(),
                        registerRequest.getProfileImgUrl(),
                        oauthInfo);
        return OauthRegisterResponse.from(tokenGenerateHelper.execute(user));
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
