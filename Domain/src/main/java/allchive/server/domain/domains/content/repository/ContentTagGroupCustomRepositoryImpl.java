package allchive.server.domain.domains.content.repository;

import static allchive.server.domain.domains.content.domain.QContent.content;
import static allchive.server.domain.domains.content.domain.QContentTagGroup.contentTagGroup;
import static allchive.server.domain.domains.content.domain.QTag.tag;

import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.ContentTagGroup;
import allchive.server.domain.domains.content.domain.Tag;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContentTagGroupCustomRepositoryImpl implements ContentTagGroupCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ContentTagGroup> queryContentTagGroupByContentIn(List<Content> contentList) {
        return queryFactory
                .selectFrom(contentTagGroup)
                .join(contentTagGroup.tag, tag)
                .fetchJoin()
                .where(contentIdIn(contentList))
                .orderBy(createdAtDesc())
                .fetch();
    }

    @Override
    public List<ContentTagGroup> queryContentTagGroupByContentWithTag(Content content) {
        return queryFactory
                .selectFrom(contentTagGroup)
                .join(contentTagGroup.tag, tag)
                .fetchJoin()
                .where(contentEq(content))
                .orderBy(createdAtDesc())
                .fetch();
    }

    @Override
    public List<ContentTagGroup> queryContentTagGroupByTagInWithContent(List<Tag> tags) {
        return queryFactory
                .selectFrom(contentTagGroup)
                .join(contentTagGroup.content, content)
                .fetchJoin()
                .where(tagIn(tags))
                .orderBy(createdAtDesc())
                .fetch();
    }

    @Override
    public List<ContentTagGroup> queryContentTagGroupByTagIdInWithContent(List<Long> tagIds) {
        return queryFactory
                .selectFrom(contentTagGroup)
                .join(contentTagGroup.content, content)
                .fetchJoin()
                .where(tagIdIn(tagIds))
                .fetch();
    }

    private BooleanExpression contentIdIn(List<Content> contentList) {
        return contentTagGroup.content.in(contentList);
    }

    private BooleanExpression contentEq(Content content) {
        return contentTagGroup.content.eq(content);
    }

    private BooleanExpression tagIn(List<Tag> tagList) {
        return contentTagGroup.tag.in(tagList);
    }

    private BooleanExpression tagIdIn(List<Long> tagIds) {
        if (tagIds == null) return null;
        return contentTagGroup.tag.id.in(tagIds);
    }

    private OrderSpecifier<LocalDateTime> createdAtDesc() {
        return contentTagGroup.createdAt.desc();
    }
}
