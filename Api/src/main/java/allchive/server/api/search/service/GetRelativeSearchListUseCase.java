package allchive.server.api.search.service;

import static allchive.server.core.consts.AllchiveConst.SEARCH_KEY;
import static jodd.util.StringPool.ASTERISK;

import allchive.server.api.search.model.dto.request.SearchRequest;
import allchive.server.api.search.model.dto.response.SearchListResponse;
import allchive.server.core.annotation.UseCase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

@UseCase
@RequiredArgsConstructor
public class GetRelativeSearchListUseCase {
    private final RedisTemplate<String, Object> redisTemplate;

    public SearchListResponse execute(SearchRequest request) {
        HashOperations<String, String, Long> hashOperations = redisTemplate.opsForHash();
        ScanOptions scanOptions = getScanOptions(request.getKeyword());
        Cursor<Entry<String, Long>> cursor = hashOperations.scan(SEARCH_KEY, scanOptions);
        return SearchListResponse.from(getRelationList(cursor));
    }

    private ScanOptions getScanOptions(String keyword) {
        return ScanOptions.scanOptions().match(keyword + ASTERISK).build();
    }

    private List<String> getRelationList(Cursor<Entry<String, Long>> cursor) {
        List<String> searchList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (cursor.hasNext()) {
                Entry<String, Long> entry = cursor.next();
                searchList.add(entry.getKey());
            }
        }
        return searchList;
    }
}
