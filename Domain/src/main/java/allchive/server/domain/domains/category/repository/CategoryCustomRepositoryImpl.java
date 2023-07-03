package allchive.server.domain.domains.category.repository;

import static allchive.server.domain.domains.category.domain.QCategory.category;

import allchive.server.domain.common.util.SliceUtil;
import allchive.server.domain.domains.category.domain.Category;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Category> querySliceCategoryExceptBlock(
            List<Long> categoryIdList, List<Long> blockList, Pageable pageable) {
        List<Category> categories =
                queryFactory
                        .select(category)
                        .from(category)
                        .where(userIdNotIn(blockList))
                        .orderBy(scrabListDesc(categoryIdList), scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(categories, pageable);
    }

    @Override
    public Slice<Category> querySliceCategoryByUserId(Long userId, Pageable pageable) {
        List<Category> categories =
                queryFactory
                        .select(category)
                        .from(category)
                        .where(userIdEq(userId))
                        .orderBy(pinDesc(userId), scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(categories, pageable);
    }

    @Override
    public Slice<Category> querySliceCategoryIn(List<Long> categoryIdList, Pageable pageable) {
        List<Category> categories =
                queryFactory
                        .select(category)
                        .from(category)
                        .where(categoryIdListIn(categoryIdList))
                        .orderBy(scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(categories, pageable);
    }

    private BooleanExpression userIdNotIn(List<Long> blockList) {
        return category.userId.notIn(blockList);
    }

    private BooleanExpression userIdEq(Long userId) {
        return category.userId.eq(userId);
    }

    private BooleanExpression categoryIdListIn(List<Long> categortIdList) {
        return category.id.in(categortIdList);
    }

    private OrderSpecifier<Long> scrabListDesc(List<Long> categoryIdList) {
        NumberExpression<Long> pinStatus =
                new CaseBuilder().when(category.id.in(categoryIdList)).then(1L).otherwise(0L);
        return pinStatus.desc();
    }

    private OrderSpecifier<Long> pinDesc(Long userId) {
        NumberExpression<Long> pinStatus =
                new CaseBuilder().when(category.pinUserId.contains(userId)).then(1L).otherwise(0L);
        return pinStatus.desc();
    }

    private OrderSpecifier<Long> scrapCntDesc() {
        return category.scrapCnt.desc();
    }

    private OrderSpecifier<LocalDateTime> createdAtDesc() {
        return category.createdAt.desc();
    }
}
