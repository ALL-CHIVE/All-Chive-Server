package allchive.server.domain.domains.content.repository;

import static allchive.server.domain.domains.content.domain.QTag.tag;

import allchive.server.domain.common.util.SliceUtil;
import allchive.server.domain.domains.content.domain.Tag;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

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

    @Override
    public List<Tag> queryTagByUserIdContainName(Long userId, String name) {
        return queryFactory.selectFrom(tag).where(userIdEq(userId), titleContain(name)).fetch();
    }

    @Override
    public Slice<Tag> querySliceTag(Pageable pageable) {
        List<Tag> tags =
                queryFactory
                        .selectFrom(tag)
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(tags, pageable);
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

    private BooleanExpression userIdEq(Long userId) {
        return tag.userId.eq(userId);
    }

    private BooleanExpression titleContain(String name) {
        return tag.name.contains(name);
    }

    private OrderSpecifier<LocalDateTime> createdAtDesc() {
        return tag.createdAt.desc();
    }
}
