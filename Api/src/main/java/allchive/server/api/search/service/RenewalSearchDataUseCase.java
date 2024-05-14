package allchive.server.api.search.service;

import static allchive.server.core.consts.AllchiveConst.*;

import allchive.server.api.common.util.StringUtil;
import allchive.server.core.annotation.UseCase;
import allchive.server.domain.domains.archiving.adaptor.ArchivingAdaptor;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.content.adaptor.TagAdaptor;
import allchive.server.domain.domains.content.domain.Tag;

import java.util.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.redis.core.RedisCallback;
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
        long startTime = System.currentTimeMillis();
        delete();
        renewalArchiving();
        renewalTag();
        long stopTime = System.currentTimeMillis();
        log.info("{}", stopTime - startTime);
    }

    private void delete() {
        redisTemplate.executePipelined((RedisCallback<Object>)
                redisConnection -> {
                    for (char c = 'A'; c <= 'Z'; c++) {
                        String key = SEARCH_KEY + c;
                        redisConnection.del(key.getBytes());
                    }

                    for (int index = 0; index < 19; index++) {
                        String key = SEARCH_KEY + KOREAN_ALPHA[index];
                        redisConnection.del(key.getBytes());
                    }

                    for (int index = 0; index < 9; index++) {
                        String key = SEARCH_KEY + index;
                        redisConnection.del(key.getBytes());
                    }

                    redisConnection.del((SEARCH_KEY + '?').getBytes());
                    return null;
                });
    }

    private void renewalTag() {
        int pageNum = 0;
        while (true) {
            PageRequest pageRequest = PageRequest.of(pageNum, BULK_SIZE);
            Slice<Tag> slicedTags = tagAdaptor.querySliceTag(pageRequest);
            Set<Tag> tags = new HashSet<>(slicedTags.getContent());
            redisTemplate.executePipelined(
                    (RedisCallback<Object>)
                            redisConnection -> {
                                tags.forEach(
                                        tag -> {
                                            String key;
                                            if (StringUtil.isKorean(
                                                    tag.getName().trim().charAt(0))) {
                                                key =
                                                        SEARCH_KEY
                                                                + StringUtil.extractKoreanInitial(
                                                                tag.getName()
                                                                        .trim()
                                                                        .charAt(0));
                                            } else {
                                                key =
                                                        SEARCH_KEY
                                                                + tag.getName()
                                                                .trim()
                                                                .toUpperCase()
                                                                .charAt(0);
                                            }
                                            redisConnection
                                                    .zSetCommands()
                                                    .zAdd(
                                                            key.getBytes(),
                                                            0,
                                                            (tag.getName().trim() + ASTERISK)
                                                                    .getBytes());
                                            for (int index = 0;
                                                 index <= tag.getName().length();
                                                 index++) {
                                                redisConnection
                                                        .zSetCommands()
                                                        .zAdd(
                                                                key.getBytes(),
                                                                0,
                                                                tag.getName()
                                                                        .trim()
                                                                        .substring(0, index)
                                                                        .getBytes());
                                            }
                                        });
                                return null;
                            });

            if (!slicedTags.hasNext()) {
                break;
            }
            pageNum++;
        }
    }

    private void renewalArchiving() {
        int pageNum = 0;
        while (true) {
            PageRequest pageRequest = PageRequest.of(pageNum, BULK_SIZE);
            Slice<Archiving> slicedArchivings =
                    archivingAdaptor.querySliceArchivingByPublicStatus(pageRequest, true);
            Set<Archiving> archivings = new HashSet<>(slicedArchivings.getContent());
            redisTemplate.executePipelined(
                    (RedisCallback<Object>)
                            redisConnection -> {
                                archivings.forEach(
                                        archiving -> {
                                            String key;
                                            if (StringUtil.isKorean(
                                                    archiving.getTitle().trim().charAt(0))) {
                                                key =
                                                        SEARCH_KEY
                                                                + StringUtil.extractKoreanInitial(
                                                                archiving
                                                                        .getTitle()
                                                                        .trim()
                                                                        .charAt(0));
                                            } else {
                                                key =
                                                        SEARCH_KEY
                                                                + archiving
                                                                .getTitle()
                                                                .trim()
                                                                .toUpperCase()
                                                                .charAt(0);
                                            }
                                            redisConnection
                                                    .zSetCommands()
                                                    .zAdd(
                                                            key.getBytes(),
                                                            0,
                                                            (archiving.getTitle().trim() + ASTERISK)
                                                                    .getBytes());
                                            for (int index = 0;
                                                 index <= archiving.getTitle().length();
                                                 index++) {
                                                redisConnection
                                                        .zSetCommands()
                                                        .zAdd(
                                                                key.getBytes(),
                                                                0,
                                                                archiving
                                                                        .getTitle()
                                                                        .trim()
                                                                        .substring(0, index)
                                                                        .getBytes());
                                            }
                                        });
                                return null;
                            });

            if (!slicedArchivings.hasNext()) {
                break;
            }
            pageNum++;
        }
    }
}