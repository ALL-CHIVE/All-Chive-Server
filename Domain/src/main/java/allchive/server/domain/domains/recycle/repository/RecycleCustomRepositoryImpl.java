package allchive.server.domain.domains.recycle.repository;

import static allchive.server.domain.domains.recycle.domain.QRecycle.recycle;

import allchive.server.domain.domains.recycle.domain.Recycle;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecycleCustomRepositoryImpl implements RecycleCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Recycle> queryRecycleByUserIdInArchivingIdListAndContentIdList(
            List<Long> archivingIds, List<Long> contentIds, Long userId) {
        return queryFactory
                .selectFrom(recycle)
                .where(archivingIdInOrContentIdIn(archivingIds, contentIds))
                .orderBy(createdAtDesc())
                .fetch();
    }

    private BooleanExpression archivingIdInOrContentIdIn(
            List<Long> archivingIdList, List<Long> contentIdList) {
        return recycle.archivingId.in(archivingIdList).or(recycle.contentId.in(contentIdList));
    }

    private OrderSpecifier<LocalDateTime> createdAtDesc() {
        return recycle.createdAt.desc();
    }
}
