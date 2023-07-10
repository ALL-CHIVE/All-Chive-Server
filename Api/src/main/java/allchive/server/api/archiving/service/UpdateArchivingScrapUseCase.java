package allchive.server.api.archiving.service;


import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.archiving.validator.ArchivingValidator;
import allchive.server.domain.domains.user.adaptor.UserAdaptor;
import allchive.server.domain.domains.user.domain.Scrap;
import allchive.server.domain.domains.user.domain.User;
import allchive.server.domain.domains.user.service.ScrapDomainService;
import allchive.server.domain.domains.user.validator.ScrapValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateArchivingScrapUseCase {
    private final ArchivingValidator archivingValidator;
    private final UserAdaptor userAdaptor;
    private final ScrapDomainService scrapDomainService;
    private final ArchivingDomainService archivingDomainService;
    private final ScrapValidator scrapValidator;

    @Transactional
    public void execute(Long archivingId, Boolean cancel) {
        archivingValidator.validateExistArchiving(archivingId);
        Long userId = SecurityUtil.getCurrentUserId();
        archivingValidator.validateDeleteStatus(archivingId, userId);
        User user = userAdaptor.queryUserById(userId);
        if (cancel) {
            scrapDomainService.deleteScrapByUserAndArchivingId(user, archivingId);
            archivingDomainService.updateScrapCount(archivingId, -1);
        } else {
            scrapValidator.validateExistScrap(user, archivingId);
            Scrap scrap = Scrap.of(user, archivingId);
            scrapDomainService.save(scrap);
            archivingDomainService.updateScrapCount(archivingId, 1);
        }
    }
}