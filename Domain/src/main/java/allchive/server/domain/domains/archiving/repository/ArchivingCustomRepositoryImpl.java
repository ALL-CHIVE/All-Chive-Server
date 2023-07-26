package allchive.server.domain.domains.archiving.repository;

import static allchive.server.domain.domains.archiving.domain.QArchiving.archiving;

import allchive.server.domain.common.util.SliceUtil;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.domain.enums.Category;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@RequiredArgsConstructor
public class ArchivingCustomRepositoryImpl implements ArchivingCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Archiving> querySliceArchivingExceptBlock(
            List<Long> archivingIdList,
            List<Long> blockList,
            Category category,
            Pageable pageable) {
        List<Archiving> archivings =
                queryFactory
                        .select(archiving)
                        .from(archiving)
                        .where(
                                userIdNotIn(blockList),
                                publicStatusTrue(),
                                categoryEq(category),
                                deleteStatusFalse())
                        .orderBy(scrabListDesc(archivingIdList), scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(archivings, pageable);
    }

    @Override
    public Slice<Archiving> querySliceArchivingByUserId(
            Long userId, Category category, Pageable pageable) {
        List<Archiving> archivings =
                queryFactory
                        .select(archiving)
                        .from(archiving)
                        .where(userIdEq(userId), categoryEq(category), deleteStatusFalse())
                        .orderBy(pinDesc(userId), scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(archivings, pageable);
    }

    @Override
    public Slice<Archiving> querySliceArchivingByIdIn(
            List<Long> archivingIdList, Category category, Pageable pageable) {
        List<Archiving> archivings =
                queryFactory
                        .select(archiving)
                        .from(archiving)
                        .where(
                                archivingIdListIn(archivingIdList),
                                publicStatusTrue(),
                                categoryEq(category),
                                deleteStatusFalse())
                        .orderBy(scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(archivings, pageable);
    }

    @Override
    public List<Archiving> queryArchivingByUserId(Long userId) {
        return queryFactory
                .selectFrom(archiving)
                .where(userIdEq(userId), deleteStatusFalse())
                .orderBy(categoryDesc(), createdAtDesc())
                .fetch();
    }

    @Override
    public boolean queryArchivingExistById(Long archivingId) {
        Archiving fetchOne =
                queryFactory.selectFrom(archiving).where(archivingIdEq(archivingId)).fetchFirst();
        return fetchOne != null;
    }

    @Override
    public Slice<Archiving> querySliceArchivingByUserIdAndKeywordsOrderByTagArchvingIds(
            Long userId, String keyword, Pageable pageable, Set<Long> tagArchivingIds) {
        List<Archiving> archivings =
                queryFactory
                        .selectFrom(archiving)
                        .where(userIdEq(userId), titleContainOrIdIn(keyword, tagArchivingIds))
                        .orderBy(idIn(tagArchivingIds), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(archivings, pageable);
    }

    @Override
    public Slice<Archiving> querySliceArchivingByKeywordExceptBlockOrderByTagArchvingIds(
            List<Long> archivingIdList, List<Long> blockList, String keyword, Pageable pageable, Set<Long> tagArchivingIds) {
        List<Archiving> archivings =
                queryFactory
                        .select(archiving)
                        .from(archiving)
                        .where(
                                userIdNotIn(blockList),
                                publicStatusTrue(),
                                deleteStatusFalse(),
                                titleContainOrIdIn(keyword, tagArchivingIds))
                        .orderBy(idIn(tagArchivingIds), scrabListDesc(archivingIdList), scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(archivings, pageable);
    }

    private BooleanExpression userIdNotIn(List<Long> blockList) {
        return archiving.userId.notIn(blockList);
    }

    private BooleanExpression publicStatusTrue() {
        return archiving.publicStatus.eq(Boolean.TRUE);
    }

    private BooleanExpression categoryEq(Category category) {
        if (category.equals(Category.ALL)) {
            return null;
        }
        return archiving.category.eq(category);
    }

    private BooleanExpression deleteStatusFalse() {
        return archiving.deleteStatus.eq(Boolean.FALSE);
    }

    private BooleanExpression userIdEq(Long userId) {
        return archiving.userId.eq(userId);
    }

    private BooleanExpression archivingIdListIn(List<Long> archivingIdList) {
        return archiving.id.in(archivingIdList);
    }

    private BooleanExpression archivingIdEq(Long archivingId) {
        return archiving.id.eq(archivingId);
    }

    private BooleanExpression titleContainOrIdIn(String keyword, Set<Long> tagArchivingIds) {
        return archiving.title.contains(keyword).or(archiving.id.in(tagArchivingIds));
    }

    private OrderSpecifier<Long> scrabListDesc(List<Long> archivingIdList) {
        NumberExpression<Long> pinStatus =
                new CaseBuilder().when(archiving.id.in(archivingIdList)).then(1L).otherwise(0L);
        return pinStatus.desc();
    }

    private OrderSpecifier<Long> pinDesc(Long userId) {
        NumberExpression<Long> pinStatus =
                new CaseBuilder().when(archiving.pinUserId.contains(userId)).then(1L).otherwise(0L);
        return pinStatus.desc();
    }

    private OrderSpecifier<Long> scrapCntDesc() {
        return archiving.scrapCnt.desc();
    }

    private OrderSpecifier<LocalDateTime> createdAtDesc() {
        return archiving.createdAt.desc();
    }

    private OrderSpecifier<Category> categoryDesc() {
        return archiving.category.desc();
    }

    private OrderSpecifier<Long> idIn(Set<Long> tagArchivingIds) {
        NumberExpression<Long> idInStatus =
                new CaseBuilder().when(archiving.id.in(tagArchivingIds)).then(1L).otherwise(0L);
        return idInStatus.desc();
    }
}
