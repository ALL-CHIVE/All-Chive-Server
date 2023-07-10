package allchive.server.domain.domains.archiving.repository;

import static allchive.server.domain.domains.archiving.domain.QArchiving.archiving;

import allchive.server.domain.common.util.SliceUtil;
import allchive.server.domain.domains.archiving.domain.Archiving;
import allchive.server.domain.domains.archiving.domain.enums.Subject;
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
public class ArchivingCustomRepositoryImpl implements ArchivingCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Archiving> querySliceArchivingExceptBlock(
            List<Long> archivingIdList, List<Long> blockList, Subject subject, Pageable pageable) {
        List<Archiving> archivings =
                queryFactory
                        .select(archiving)
                        .from(archiving)
                        .where(
                                userIdNotIn(blockList),
                                publicStatusTrue(),
                                subjectEq(subject),
                                deleteStatusFalse())
                        .orderBy(scrabListDesc(archivingIdList), scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(archivings, pageable);
    }

    @Override
    public Slice<Archiving> querySliceArchivingByUserId(
            Long userId, Subject subject, Pageable pageable) {
        List<Archiving> archivings =
                queryFactory
                        .select(archiving)
                        .from(archiving)
                        .where(userIdEq(userId), subjectEq(subject), deleteStatusFalse())
                        .orderBy(pinDesc(userId), scrapCntDesc(), createdAtDesc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return SliceUtil.toSlice(archivings, pageable);
    }

    @Override
    public Slice<Archiving> querySliceArchivingIn(
            List<Long> archivingIdList, Subject subject, Pageable pageable) {
        List<Archiving> archivings =
                queryFactory
                        .select(archiving)
                        .from(archiving)
                        .where(
                                archivingIdListIn(archivingIdList),
                                publicStatusTrue(),
                                subjectEq(subject),
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
                .orderBy(subjectDesc(), createdAtDesc())
                .fetch();
    }

    @Override
    public boolean queryArchivingExist(Long archivingId) {
        Archiving fetchOne =
                queryFactory.selectFrom(archiving).where(archivingIdEq(archivingId)).fetchFirst();
        return fetchOne != null;
    }

    private BooleanExpression userIdNotIn(List<Long> blockList) {
        return archiving.userId.notIn(blockList);
    }

    private BooleanExpression publicStatusTrue() {
        return archiving.publicStatus.eq(Boolean.TRUE);
    }

    private BooleanExpression subjectEq(Subject subject) {
        if (subject.equals(Subject.ALL)) {
            return null;
        }
        return archiving.subject.eq(subject);
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

    private OrderSpecifier<Subject> subjectDesc() {
        return archiving.subject.desc();
    }
}
