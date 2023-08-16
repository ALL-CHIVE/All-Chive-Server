package allchive.server.api.auth.service;


import allchive.server.api.auth.service.helper.OauthHelper;
import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.core.error.exception.InvalidOauthProviderException;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.block.service.BlockDomainService;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Tag;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.service.ContentTagGroupDomainService;
import allchive.server.domain.domains.content.service.TagDomainService;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import allchive.server.domain.domains.report.service.ReportDomainService;
import allchive.server.domain.domains.search.service.LatestSearchDomainService;
import allchive.server.domain.domains.user.adaptor.RefreshTokenAdaptor;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.domain.enums.OauthProvider;
import allchive.server.domain.domains.user.service.ScrapDomainService;
import allchive.server.domain.domains.user.service.UserDomainService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class WithdrawUserUseCase {
    private final UserAdaptor userAdaptor;
    private final OauthHelper oauthHelper;
    private final RefreshTokenAdaptor refreshTokenAdaptor;
    private final LatestSearchDomainService latestSearchDomainService;
    private final ScrapDomainService scrapDomainService;
    private final BlockDomainService blockDomainService;
    private final ArchivingAdaptor archivingAdaptor;
    private final TagAdaptor tagAdaptor;
    private final ContentTagGroupDomainService contentTagGroupDomainService;
    private final ContentDomainService contentDomainService;
    private final TagDomainService tagDomainService;
    private final RecycleDomainService recycleDomainService;
    private final ReportDomainService reportDomainService;
    private final UserDomainService userDomainService;
    private final ArchivingDomainService archivingDomainService;

    @Transactional
    public void execute(String appleAccessToken, String referer) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userAdaptor.findById(userId);
        // oauth쪽 탈퇴
        withdrawOauth(user.getOauthInfo().getProvider(), appleAccessToken, user, referer);
        // 우리쪽 탈퇴
        withdrawService(userId, user);
    }

    @Transactional
    public void executeDev(String appleAccessToken) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userAdaptor.findById(userId);
        // oauth쪽 탈퇴
        withdrawOauthDev(user.getOauthInfo().getProvider(), appleAccessToken, user);
        // 우리쪽 탈퇴
        withdrawService(userId, user);
    }

    private void withdrawOauth(
            OauthProvider provider, String appleAccessToken, User user, String referer) {
        switch (provider) {
            case KAKAO -> oauthHelper.withdraw(provider, user.getOauthInfo().getOid(), null, null);
            case APPLE -> oauthHelper.withdraw(provider, null, appleAccessToken, referer);
            default -> throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    private void withdrawOauthDev(OauthProvider provider, String appleAccessToken, User user) {
        switch (provider) {
            case KAKAO -> oauthHelper.withdrawDev(provider, user.getOauthInfo().getOid(), null);
            case APPLE -> oauthHelper.withdrawDev(provider, null, appleAccessToken);
            default -> throw InvalidOauthProviderException.EXCEPTION;
        }
    }

    private void withdrawService(Long userId, User user) {
        refreshTokenAdaptor.deleteTokenByUserId(userId);
        latestSearchDomainService.deleteAllByUserId(userId);
        scrapDomainService.deleteAllByUser(user);
        blockDomainService.queryDeleteBlockByBlockFromOrBlockUser(userId);
        List<Archiving> archivingList = archivingAdaptor.findAllByUserId(userId);
        List<Long> archivingId = archivingList.stream().map(Archiving::getId).toList();
        List<Tag> tagList = tagAdaptor.findAllByUserId(userId);
        contentTagGroupDomainService.deleteAllByTagIn(tagList);
        tagDomainService.deleteAll(tagList);
        contentDomainService.deleteAllByArchivingIdIn(archivingId);
        recycleDomainService.deleteAllByUserId(userId);
        reportDomainService.deleteAllByReportedUserId(userId);
        archivingDomainService.deleteAllById(archivingId);
        userDomainService.deleteUserById(userId);
    }
}
