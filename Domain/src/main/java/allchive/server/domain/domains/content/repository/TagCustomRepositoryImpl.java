package allchive.server.domain.domains.content.repository;

import static allchive.server.domain.domains.content.domain.QTag.tag;

import allchive.server.domain.domains.content.domain.Tag;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

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

    @Override
    public List<Tag> queryTagByTagIdIn(List<Long> tagIds) {
        return queryFactory.selectFrom(tag).where(tagIdIn(tagIds)).fetch();
    }

    private BooleanExpression tagUserIdEq(Long userId) {
        return tag.userId.eq(userId);
    }

    private BooleanExpression usedAtNotNull() {
        return tag.usedAt.isNotNull();
    }

    private BooleanExpression tagIdIn(List<Long> tagIds) {
        return tag.id.in(tagIds);
    }

    private OrderSpecifier<LocalDateTime> createdAtDesc() {
        return tag.createdAt.desc();
    }
}
