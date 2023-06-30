package allchive.server.api.auth.service;


import allchive.server.api.auth.model.dto.response.OauthLoginLinkResponse;
import allchive.server.api.auth.service.helper.KakaoOauthHelper;
import allchive.server.core.annotation.UseCase;
import allchive.server.core.error.exception.InvalidOauthProviderException;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class OauthLinkUseCase {
    private final KakaoOauthHelper kakaoOauthHelper;

    public OauthLoginLinkResponse getKaKaoOauthLinkTest(OauthProvider provider) {
        switch (provider) {
            case KAKAO:
                return new OauthLoginLinkResponse(kakaoOauthHelper.getKaKaoOauthLinkTest());
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    public OauthLoginLinkResponse getKaKaoOauthLink(OauthProvider provider, String referer) {
        switch (provider) {
            case KAKAO:
                return new OauthLoginLinkResponse(kakaoOauthHelper.getKaKaoOauthLink(referer));
            default:
                throw InvalidOauthProviderException.EXCEPTION;
        }
    }
}
