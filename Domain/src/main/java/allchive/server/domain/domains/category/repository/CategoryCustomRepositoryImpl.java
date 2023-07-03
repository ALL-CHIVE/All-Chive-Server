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
            List<Long> blockList, Long userId, Pageable pageable) {
        List<Category> categories =
                queryFactory
                        .select(category)
                        .from(category)
                        .where(userIdNotIn(blockList))
                        .orderBy(pinDesc(userId), scrapDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(categories, pageable);
    }

    private BooleanExpression userIdNotIn(List<Long> blockList) {
        return category.userId.notIn(blockList);
    }

    // TODO : 데이터 없으면 오류뜨는거 처리
    private OrderSpecifier<Long> pinDesc(Long userId) {
        NumberExpression<Long> pinStatus =
                new CaseBuilder().when(category.pinUserId.contains(userId)).then(1L).otherwise(0L);
        return pinStatus.desc();
    }

    private OrderSpecifier<Long> scrapDesc() {
        return category.scrapCnt.desc();
    }

    private OrderSpecifier<LocalDateTime> createdAtDesc() {
        return category.createdAt.desc();
    }
}
