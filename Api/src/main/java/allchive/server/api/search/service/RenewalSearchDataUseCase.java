package allchive.server.api.search.service;

import static allchive.server.core.consts.AllchiveConst.ASTERISK;
import static allchive.server.core.consts.AllchiveConst.SEARCH_KEY;

import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Tag;
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
public class RenewalSearchDataUseCase {
    private final ArchivingAdaptor archivingAdaptor;
    private final TagAdaptor tagAdaptor;
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
        renewalArchiving();
        renewalTag();
    }

    private void renewalTag() {
        Set<Tag> tags = new HashSet<>(tagAdaptor.findAll());
        tags.forEach(
                tag -> {
                    redisTemplate.opsForZSet().add(SEARCH_KEY, tag.getName().trim() + ASTERISK, 0);
                    for (int index = 0; index <= tag.getName().length(); index++) {
                        redisTemplate
                                .opsForZSet()
                                .add(SEARCH_KEY, tag.getName().trim().substring(0, index), 0);
                    }
                });
    }

    private void renewalArchiving() {
        Set<Archiving> archivings =
                new HashSet<>(archivingAdaptor.findAllByPublicStatus(Boolean.TRUE));
        archivings.forEach(
                archiving -> {
                    redisTemplate
                            .opsForZSet()
                            .add(SEARCH_KEY, archiving.getTitle().trim() + ASTERISK, 0);
                    for (int index = 0; index <= archiving.getTitle().length(); index++) {
                        redisTemplate
                                .opsForZSet()
                                .add(
                                        SEARCH_KEY,
                                        archiving.getTitle().trim().substring(0, index),
                                        0);
                    }
                });
    }
}
