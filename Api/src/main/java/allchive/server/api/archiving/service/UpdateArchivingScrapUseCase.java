package allchive.server.api.archiving.service;

import static allchive.server.core.consts.AllchiveConst.MINUS_ONE;
import static allchive.server.core.consts.AllchiveConst.PLUS_ONE;

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
        Long userId = SecurityUtil.getCurrentUserId();
        validateExecution(archivingId, userId, cancel);
        User user = userAdaptor.findById(userId);
        if (cancel) {
            scrapDomainService.deleteScrapByUserAndArchivingId(user, archivingId);
            archivingDomainService.updateScrapCount(archivingId, MINUS_ONE);
        } else {
            Scrap scrap = Scrap.of(user, archivingId);
            scrapDomainService.save(scrap);
            archivingDomainService.updateScrapCount(archivingId, PLUS_ONE);
        }
    }

    private void validateExecution(Long archivingId, Long userId, Boolean cancel) {
        archivingValidator.validateNotDeleteExceptUser(archivingId, userId);
        if (cancel) {
            scrapValidator.validateExistScrap(userId, archivingId);
        } else {
            scrapValidator.validateNotExistScrap(userId, archivingId);
        }
    }
}
