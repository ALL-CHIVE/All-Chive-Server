package allchive.server.api.auth.service;


import allchive.server.api.auth.service.helper.OauthHelper;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.core.error.exception.InvalidOauthProviderException;
import allchive.server.domain.domains.user.adaptor.RefreshTokenAdaptor;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import allchive.server.domain.domains.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class WithdrawUserUseCase {
    private final UserAdaptor userAdaptor;
    private final RefreshTokenAdaptor refreshTokenAdaptor;
    private final OauthHelper oauthHelper;
    private final UserDomainService userDomainService;

    public void execute(OauthProvider provider, String appleAccessToken) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userAdaptor.findUserById(userId);
        // 우리쪽 탈퇴
        refreshTokenAdaptor.deleteTokenByUserId(userId);
        // oauth쪽 탈퇴
        switch (provider) {
            case KAKAO -> oauthHelper.withdraw(provider, user.getOauthInfo().getOid(), null);
            case APPLE -> oauthHelper.withdraw(provider, null, appleAccessToken);
            default -> throw InvalidOauthProviderException.EXCEPTION;
        }
        userDomainService.deleteUserById(userId);
    }
}
