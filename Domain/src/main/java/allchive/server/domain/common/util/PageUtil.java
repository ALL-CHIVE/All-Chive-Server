package allchive.server.domain.common.util;


import com.querydsl.core.QueryResults;
import java.util.List;
import org.springframework.data.domain.*;

public class PageUtil {
    public static <T> Page<T> toPage(QueryResults<T> results, Pageable pageable) {
        boolean hasNext = hasNext(results.getResults(), pageable);
        return new PageImpl<>(
                hasNext ? getContent(results.getResults(), pageable) : results.getResults(),
                pageable,
                results.getTotal());
    }

    // 다음 페이지 있는지 확인
    private static <T> boolean hasNext(List<T> content, Pageable pageable) {
        return pageable.isPaged() && content.size() > pageable.getPageSize();
    }

    // 데이터 1개 빼고 반환
    private static <T> List<T> getContent(List<T> content, Pageable pageable) {
        return content.subList(0, pageable.getPageSize());
    }
}
