package allchive.server.domain.domains.content.repository;

import static allchive.server.domain.domains.content.domain.QContent.content;

import allchive.server.domain.common.util.SliceUtil;
import allchive.server.domain.domains.content.domain.Content;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@RequiredArgsConstructor
public class ContentCustomRepositoryImpl implements ContentCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Content> querySliceContentByArchivingId(Long archivingId, Pageable pageable) {
        List<Content> archivings =
                queryFactory
                        .selectFrom(content)
                        .where(archivingIdEq(archivingId))
                        .orderBy(createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(archivings, pageable);
    }

    private BooleanExpression archivingIdEq(Long archivingId) {
        return content.archivingId.eq(archivingId);
    }

    private OrderSpecifier<LocalDateTime> createdAtDesc() {
        return content.createdAt.desc();
    }
}
