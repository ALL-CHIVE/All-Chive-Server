package allchive.server.domain.domains.category.repository;

import static allchive.server.domain.domains.category.domain.QCategory.category;

import allchive.server.domain.common.util.SliceUtil;
import allchive.server.domain.domains.category.domain.Category;
import allchive.server.domain.domains.category.domain.enums.Subject;
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
            List<Long> categoryIdList, List<Long> blockList, Subject subject, Pageable pageable) {
        List<Category> categories =
                queryFactory
                        .select(category)
                        .from(category)
                        .where(
                                userIdNotIn(blockList),
                                publicStatusTrue(),
                                subjectEq(subject),
                                deleteStatusFalse())
                        .orderBy(scrabListDesc(categoryIdList), scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(categories, pageable);
    }

    @Override
    public Slice<Category> querySliceCategoryByUserId(
            Long userId, Subject subject, Pageable pageable) {
        List<Category> categories =
                queryFactory
                        .select(category)
                        .from(category)
                        .where(userIdEq(userId), subjectEq(subject), deleteStatusFalse())
                        .orderBy(pinDesc(userId), scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(categories, pageable);
    }

    @Override
    public Slice<Category> querySliceCategoryIn(
            List<Long> categoryIdList, Subject subject, Pageable pageable) {
        List<Category> categories =
                queryFactory
                        .select(category)
                        .from(category)
                        .where(
                                categoryIdListIn(categoryIdList),
                                publicStatusTrue(),
                                subjectEq(subject),
                                deleteStatusFalse())
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
                .orderBy(subjectDesc(), createdAtDesc())
                .fetch();
    }

    @Override
    public boolean queryCategoryExist(Long categoryId) {
        Category fetchOne =
                queryFactory.selectFrom(category).where(categoryIdEq(categoryId)).fetchFirst();
        return fetchOne != null;
    }

    private BooleanExpression userIdNotIn(List<Long> blockList) {
        return category.userId.notIn(blockList);
    }

    private BooleanExpression publicStatusTrue() {
        return category.publicStatus.eq(Boolean.TRUE);
    }

    private BooleanExpression subjectEq(Subject subject) {
        if (subject.equals(Subject.ALL)) {
            return null;
        }
        return category.subject.eq(subject);
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

    private BooleanExpression categoryIdEq(Long categoryId) {
        return category.id.eq(categoryId);
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

    private OrderSpecifier<Subject> subjectDesc() {
        return category.subject.desc();
    }
}
