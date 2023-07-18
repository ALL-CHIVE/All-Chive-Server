package allchive.server.api.auth.service;


import allchive.server.api.auth.service.helper.OauthHelper;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.core.error.exception.InvalidOauthProviderException;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.service.ContentTagGroupDomainService;
import allchive.server.domain.domains.content.service.TagDomainService;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import allchive.server.domain.domains.search.service.LatestSearchDomainService;
import allchive.server.domain.domains.user.adaptor.RefreshTokenAdaptor;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import allchive.server.domain.domains.user.service.ScrapDomainService;
import allchive.server.domain.domains.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class WithdrawUserUseCase {
    private final UserAdaptor userAdaptor;
    private final OauthHelper oauthHelper;
    private final RefreshTokenAdaptor refreshTokenAdaptor;
    private final LatestSearchDomainService latestSearchDomainService;
    private final ScrapDomainService scrapDomainService;
    private final ArchivingAdaptor archivingAdaptor;
    private final ContentAdaptor contentAdaptor;
    private final ContentTagGroupDomainService contentTagGroupDomainService;
    private final ContentDomainService contentDomainService;
    private final TagDomainService tagDomainService;
    private final RecycleDomainService recycleDomainService;
    private final UserDomainService userDomainService;

    public void execute(OauthProvider provider, String appleAccessToken) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userAdaptor.findUserById(userId);
        // oauth쪽 탈퇴
        withdrawOauth(provider, appleAccessToken, user);
        // 우리쪽 탈퇴
        withdrawService(userId);
    }

    private void withdrawOauth(OauthProvider provider, String appleAccessToken, User user) {
        switch (provider) {
            case KAKAO -> oauthHelper.withdraw(provider, user.getOauthInfo().getOid(), null);
            case APPLE -> oauthHelper.withdraw(provider, null, appleAccessToken);
            default -> throw InvalidOauthProviderException.EXCEPTION;
        }

    }

    // refreshtoken
    // latestsearch
    // scrap
    // block
    // archiving
    // contenttaggroup
    // tag
    // content
    // recycle
    // user
    private void withdrawService(Long userId) {
        refreshTokenAdaptor.deleteTokenByUserId(userId);



        userDomainService.deleteUserById(userId);
    }
}
