package allchive.server.api.auth.service;


import allchive.server.api.auth.model.dto.response.OauthLoginLinkResponse;
import allchive.server.api.auth.service.helper.AppleOAuthHelper;
import allchive.server.api.auth.service.helper.KakaoOauthHelper;
import allchive.server.core.annotation.UseCase;
import allchive.server.core.error.exception.InvalidOauthProviderException;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class OauthLinkUseCase {
    private final KakaoOauthHelper kakaoOauthHelper;
    private final AppleOAuthHelper appleOAuthHelper;

    public OauthLoginLinkResponse getOauthLinkDev(OauthProvider provider) {
        switch (provider) {
            case KAKAO:
                return new OauthLoginLinkResponse(kakaoOauthHelper.getKaKaoOauthLinkDev());
            case APPLE:
                return new OauthLoginLinkResponse(appleOAuthHelper.getAppleOauthLinkDev());
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    public OauthLoginLinkResponse getOauthLink(OauthProvider provider, String referer) {
        switch (provider) {
            case KAKAO:
                return new OauthLoginLinkResponse(kakaoOauthHelper.getKaKaoOauthLink(referer));
            case APPLE:
                return new OauthLoginLinkResponse(appleOAuthHelper.getAppleOAuthLink(referer));
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }
}
