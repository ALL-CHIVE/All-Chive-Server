package allchive.server.api.search.service;

import static allchive.server.core.consts.AllchiveConst.ASTERISK;
import static allchive.server.core.consts.AllchiveConst.SEARCH_KEY;

import allchive.server.api.common.util.StringUtil;
import allchive.server.api.search.model.dto.response.SearchListResponse;
import allchive.server.core.annotation.UseCase;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.*;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetRelativeSearchListUseCase {
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    public SearchListResponse execute(String word) {
        validateExecution(word);
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        List<String> autoCompleteList = new ArrayList<>();
        Long rank = zSetOperations.rank(SEARCH_KEY, word);
        if (rank != null) {
            Set<String> rangeList = zSetOperations.range(SEARCH_KEY, rank, rank + 1000);
            autoCompleteList = getAutoCompleteList(rangeList, word);
        }
        return SearchListResponse.from(autoCompleteList);
    }

    private void validateExecution(String word) {
        StringUtil.checkEmptyString(word);
    }

    private List<String> getAutoCompleteList(Set<String> rangeList, String keyword) {
        return rangeList.stream()
                .filter(value -> value.endsWith(ASTERISK) && value.startsWith(keyword))
                .map(value -> StringUtils.removeEnd(value, ASTERISK))
                .limit(5)
                .toList();
    }
}
