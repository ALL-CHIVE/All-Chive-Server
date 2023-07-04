package allchive.server.domain.domains.content.repository;

import allchive.server.domain.common.util.SliceUtil;
import allchive.server.domain.domains.content.domain.Content;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;

import static allchive.server.domain.domains.content.domain.QContent.content;


@RequiredArgsConstructor
public class ContentCustomRepositoryImpl implements ContentCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Content> querySliceContentByCategoryId(Long categoryId, Pageable pageable){
        List<Content> categories =
                queryFactory
                        .selectFrom(content)
                        .where(categoryIdEq(categoryId))
                        .orderBy(createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(categories, pageable);
    }

    private BooleanExpression categoryIdEq(Long categoryId) {
        return content.categoryId.eq(categoryId);
    }

    private OrderSpecifier<LocalDateTime> createdAtDesc() {
        return content.createdAt.desc();
    }
}
