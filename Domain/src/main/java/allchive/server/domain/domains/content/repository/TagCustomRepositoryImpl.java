package allchive.server.domain.domains.content.repository;

import allchive.server.domain.domains.content.domain.Tag;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static allchive.server.domain.domains.content.domain.QTag.tag;

@RequiredArgsConstructor
public class TagCustomRepositoryImpl implements TagCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Tag> queryTagByUserIdOrderByUsedAt(Long userId) {
        return queryFactory
                .selectFrom(tag)
                .where(usedAtNotNull(), tagUserIdEq(userId))
                .orderBy(createdAtDesc())
                .fetch();
    }

    private BooleanExpression tagUserIdEq(Long userId) {
        return tag.userId.eq(userId);
    }

    private BooleanExpression usedAtNotNull() {
        return tag.usedAt.isNotNull();
    }

    private OrderSpecifier<LocalDateTime> createdAtDesc() {
        return tag.createdAt.desc();
    }
}
