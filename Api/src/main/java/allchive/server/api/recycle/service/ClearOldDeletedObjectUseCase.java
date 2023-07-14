package allchive.server.api.recycle.service;

import allchive.server.api.config.security.SecurityUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.service.ArchivingDomainService;
import allchive.server.domain.domains.content.adaptor.ContentAdaptor;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.service.ContentDomainService;
import allchive.server.domain.domains.content.service.ContentTagGroupDomainService;
import allchive.server.domain.domains.recycle.adaptor.RecycleAdaptor;
import allchive.server.domain.domains.recycle.domain.Recycle;
import allchive.server.domain.domains.recycle.domain.enums.RecycleType;
import allchive.server.domain.domains.recycle.service.RecycleDomainService;
import allchive.server.domain.domains.user.service.ScrapDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class ClearOldDeletedObjectUseCase {
    private final RecycleAdaptor recycleAdaptor;
    private final ContentAdaptor contentAdaptor;
    private final ContentTagGroupDomainService contentTagGroupDomainService;
    private final ScrapDomainService scrapDomainService;
    private final ArchivingDomainService archivingDomainService;
    private final ContentDomainService contentDomainService;
    private final RecycleDomainService recycleDomainService;

    /** 삭제 후 30일 지난 항목 제거 스케쥴러 매일 02:30에 수행 */
    @Scheduled(cron = "0 43 22 * * *")
    @Transactional
    public void executeSchedule() {
        log.info("scheduler on");
        LocalDateTime deleteStandard = LocalDateTime.now().minusDays(30);
        List<Recycle> recycles = recycleAdaptor.findAllByDeletedAtBefore(deleteStandard);

        List<Long> archivingIds =
                recycles.stream()
                        .filter(recycle -> recycle.getRecycleType().equals(RecycleType.ARCHIVING))
                        .map(Recycle::getArchivingId)
                        .toList();
        List<Long> contentIds =
                recycles.stream()
                        .filter(recycle -> recycle.getRecycleType().equals(RecycleType.CONTENT))
                        .map(Recycle::getContentId)
                        .toList();

        if (contentIds.isEmpty()) {
            contentIds = new ArrayList<>();
        }
        List<Content> contents = contentAdaptor.findAllByArchivingIds(archivingIds);
        for (Content content : contents) {
            contentIds.add(content.getId());
        }

        scrapDomainService.deleteAllByArchivingIdIn(archivingIds);
        contentTagGroupDomainService.deleteByContentIn(contents);
        contentDomainService.deleteAllById(contentIds);
        archivingDomainService.deleteAllById(archivingIds);
        recycleDomainService.deleteAll(recycles);
        log.info("scheduler off");
    }
}
