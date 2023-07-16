package allchive.server.domain.domains.report.repository;

import static allchive.server.domain.domains.report.domain.QReport.report;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportCustomRepositoryImpl implements ReportCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean queryReportExistByUserIdAndContentId(Long userId, Long contentId) {
        Integer fetchOne =
                queryFactory
                        .selectOne()
                        .from(report)
                        .where(userIdEq(userId), contentIdEq(contentId))
                        .fetchFirst(); // limit 1
        return fetchOne != null;
    }

    @Override
    public Boolean queryReportExistByUserIdAndArchivingId(Long userId, Long archivingId) {
        Integer fetchOne =
                queryFactory
                        .selectOne()
                        .from(report)
                        .where(userIdEq(userId), archivingIdEq(archivingId))
                        .fetchFirst(); // limit 1
        return fetchOne != null;
    }

    private BooleanExpression userIdEq(Long userId) {
        return report.userId.eq(userId);
    }

    private BooleanExpression contentIdEq(Long contentId) {
        return report.contentId.eq(contentId);
    }

    private BooleanExpression archivingIdEq(Long archivingId) {
        return report.archivingId.eq(archivingId);
    }
}