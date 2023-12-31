package allchive.server.domain.domains.content.repository;

import static allchive.server.domain.domains.content.domain.QContent.content;

import allchive.server.domain.common.util.SliceUtil;
import allchive.server.domain.domains.content.domain.Content;
import allchive.server.domain.domains.content.domain.enums.ContentType;
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
    public Slice<Content> querySliceContentByArchivingIdAndContentTypeAndIdIn(
            Long archivingId, Pageable pageable, ContentType contentType, List<Long> contentIds) {
        List<Content> archivings =
                queryFactory
                        .selectFrom(content)
                        .where(
                                archivingIdEq(archivingId),
                                deleteStatusFalse(),
                                contentTypeEq(contentType),
                                idIn(contentIds))
                        .orderBy(createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(archivings, pageable);
    }

    @Override
    public List<Content> queryContentInArchivingIds(List<Long> archivingIds) {
        return queryFactory
                .selectFrom(content)
                .where(archivingIdIn(archivingIds))
                .orderBy(createdAtDesc())
                .fetch();
    }

    @Override
    public boolean queryContentExistById(Long id) {
        Integer fetchOne =
                queryFactory.selectOne().from(content).where(idEq(id)).fetchFirst(); // limit 1
        return fetchOne != null;
    }

    private BooleanExpression deleteStatusFalse() {
        return content.deleteStatus.eq(Boolean.FALSE);
    }

    private BooleanExpression contentTypeEq(ContentType contentType) {
        if (contentType == null) {
            return null;
        } else {
            return content.contentType.eq(contentType);
        }
    }

    private BooleanExpression idIn(List<Long> contentIds) {
        if (contentIds == null) return null;
        return content.id.in(contentIds);
    }

    private BooleanExpression archivingIdEq(Long archivingId) {
        return content.archivingId.eq(archivingId);
    }

    private BooleanExpression archivingIdIn(List<Long> archivingIds) {
        return content.archivingId.in(archivingIds);
    }

    private BooleanExpression idEq(Long id) {
        return content.id.eq(id);
    }

    private OrderSpecifier<LocalDateTime> createdAtDesc() {
        return content.createdAt.desc();
    }
}
