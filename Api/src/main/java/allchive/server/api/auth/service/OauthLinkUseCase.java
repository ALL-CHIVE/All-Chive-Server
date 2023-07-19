package allchive.server.api.auth.service;


import allchive.server.api.auth.model.dto.response.OauthLoginLinkResponse;
import allchive.server.api.auth.service.helper.OauthHelper;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class OauthLinkUseCase {
    private final OauthHelper oauthHelper;

    public OauthLoginLinkResponse getOauthLinkDev(OauthProvider provider) {
        return oauthHelper.getOauthLinkDev(provider);
    }

    public OauthLoginLinkResponse getOauthLink(OauthProvider provider, String referer) {
        return oauthHelper.getOauthLink(provider, referer);
    }
}
