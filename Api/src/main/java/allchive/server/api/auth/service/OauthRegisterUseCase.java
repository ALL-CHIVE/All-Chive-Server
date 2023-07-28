package allchive.server.api.auth.service;


import allchive.server.api.auth.model.dto.OauthUserInfoDto;
import allchive.server.api.auth.model.dto.request.RegisterRequest;
import allchive.server.api.auth.model.dto.response.OauthRegisterResponse;
import allchive.server.api.auth.service.helper.OauthHelper;
import allchive.server.api.auth.service.helper.TokenGenerateHelper;
import allchive.server.api.common.util.UrlUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.OauthInfo;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import allchive.server.domain.domains.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class OauthRegisterUseCase {
    private final OauthHelper oauthHelper;
    private final UserDomainService userDomainService;
    private final TokenGenerateHelper tokenGenerateHelper;

    public OauthRegisterResponse execute(
            OauthProvider provider,
            String idToken,
            String oauthAccessToken,
            RegisterRequest request) {
        final OauthInfo oauthInfo = oauthHelper.getOauthInfo(provider, idToken);
        final OauthUserInfoDto oauthUserInfoDto = getUserInfo(provider, oauthAccessToken);
        final User user = registerUser(provider, oauthInfo, oauthUserInfoDto, request);
        return OauthRegisterResponse.from(tokenGenerateHelper.execute(user));
    }

    private User registerUser(
            OauthProvider provider,
            OauthInfo oauthInfo,
            OauthUserInfoDto oauthUserInfoDto,
            RegisterRequest request) {
        switch (provider) {
            case APPLE:
                return userDomainService.registerUser(
                        request.getName(),
                        request.getEmail(),
                        request.getNickname(),
                        UrlUtil.convertUrlToKey(request.getProfileImgUrl()),
                        request.getCategories(),
                        oauthInfo);
            default:
                return userDomainService.registerUser(
                        oauthUserInfoDto.getName(),
                        oauthUserInfoDto.getEmail(),
                        request.getNickname(),
                        UrlUtil.convertUrlToKey(request.getProfileImgUrl()),
                        request.getCategories(),
                        oauthInfo);
        }
    }

    private OauthUserInfoDto getUserInfo(OauthProvider provider, String oauthAccessToken) {
        switch (provider) {
            case APPLE:
                return null;
            default:
                return oauthHelper.getUserInfo(provider, oauthAccessToken);
        }
    }
}
