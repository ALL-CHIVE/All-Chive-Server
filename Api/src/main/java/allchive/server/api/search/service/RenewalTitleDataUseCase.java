package allchive.server.api.search.service;

import static allchive.server.core.consts.AllchiveConst.ASTERISK;
import static allchive.server.core.consts.AllchiveConst.SEARCH_KEY;

import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class RenewalTitleDataUseCase {
    private final ArchivingAdaptor archivingAdaptor;
    private final RedisTemplate<String, String> redisTemplate;

    @Scheduled(cron = "0 0 3 * * *")
    @Transactional(readOnly = true)
    public void executeSchedule() {
        log.info("renewal title scheduler on");
        renewalData();
        log.info("renewal title scheduler off");
    }

    @Transactional(readOnly = true)
    public void executeForce() {
        renewalData();
    }

    private void renewalData() {
        redisTemplate.delete(SEARCH_KEY);
        Set<Archiving> archivings =
                new HashSet<>(archivingAdaptor.findAllByPublicStatus(Boolean.TRUE));
        archivings.forEach(
                archiving -> {
                    redisTemplate
                            .opsForZSet()
                            .add(SEARCH_KEY, archiving.getTitle().trim() + ASTERISK, 0);
                    for (int index = 1; index < archiving.getTitle().length(); index++) {
                        redisTemplate
                                .opsForZSet()
                                .add(
                                        SEARCH_KEY,
                                        archiving.getTitle().trim().substring(0, index - 1),
                                        0);
                    }
                });
    }
}
