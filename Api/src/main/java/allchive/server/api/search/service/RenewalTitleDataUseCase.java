package allchive.server.api.search.service;

import static allchive.server.core.consts.AllchiveConst.SEARCH_KEY;

import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class RenewalTitleDataUseCase {
    private final ArchivingAdaptor archivingAdaptor;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final int TIME_LIMIT = 1;

    @Scheduled(cron = "0 0 3 * * *")
    @Transactional(readOnly = true)
    public void executeSchedule() {
        log.info("renewal title scheduler on");
        Set<Archiving> archivings =
                new HashSet<>(archivingAdaptor.findAllByPublicStatus(Boolean.TRUE));

        HashOperations<String, String, Long> hashOperations = redisTemplate.opsForHash();

        Map<String, Long> nameDataMap =
                archivings.stream()
                        .collect(Collectors.toMap(Archiving::getTitle, Archiving::getId));
        hashOperations.putAll(SEARCH_KEY, nameDataMap);

        redisTemplate.expire(SEARCH_KEY, TIME_LIMIT, TimeUnit.DAYS);
        log.info("renewal title scheduler off");
    }
}
