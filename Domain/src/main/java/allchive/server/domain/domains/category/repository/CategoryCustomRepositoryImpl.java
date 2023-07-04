package allchive.server.domain.domains.category.repository;

import static allchive.server.domain.domains.category.domain.QCategory.category;

import allchive.server.domain.common.util.SliceUtil;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.domain.enums.Topic;
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
            List<Long> categoryIdList, List<Long> blockList, Topic topic, Pageable pageable) {
        List<Category> categories =
                queryFactory
                        .select(category)
                        .from(category)
                        .where(userIdNotIn(blockList), publicStatusTrue(), topicEq(topic), deleteStatusFalse())
                        .orderBy(scrabListDesc(categoryIdList), scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(categories, pageable);
    }

    @Override
    public Slice<Category> querySliceCategoryByUserId(Long userId, Topic topic, Pageable pageable) {
        List<Category> categories =
                queryFactory
                        .select(category)
                        .from(category)
                        .where(userIdEq(userId), topicEq(topic), deleteStatusFalse())
                        .orderBy(pinDesc(userId), scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(categories, pageable);
    }

    @Override
    public Slice<Category> querySliceCategoryIn(List<Long> categoryIdList, Topic topic, Pageable pageable) {
        List<Category> categories =
                queryFactory
                        .select(category)
                        .from(category)
                        .where(categoryIdListIn(categoryIdList), publicStatusTrue(), topicEq(topic), deleteStatusFalse())
                        .orderBy(scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(categories, pageable);
    }

    @Override
    public List<Category> queryCategoryByUserId(Long userId) {
        return queryFactory
                .selectFrom(category)
                .where(userIdEq(userId), deleteStatusFalse())
                .orderBy(topicDesc(), createdAtDesc())
                .fetch();
    }

    private BooleanExpression userIdNotIn(List<Long> blockList) {
        return category.userId.notIn(blockList);
    }

    private BooleanExpression publicStatusTrue() {
        return category.publicStatus.eq(Boolean.TRUE);
    }

    private BooleanExpression topicEq(Topic topic) {
        if (topic.equals(Topic.ALL)) {
            return null;
        }
        return category.topic.eq(topic);
    }

    private BooleanExpression deleteStatusFalse() {
        return category.deleteStatus.eq(Boolean.FALSE);
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

    private OrderSpecifier<Topic> topicDesc() {
        return category.topic.desc();
    }
}
