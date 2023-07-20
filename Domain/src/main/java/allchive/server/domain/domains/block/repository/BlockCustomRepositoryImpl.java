package allchive.server.domain.domains.block.repository;

import static allchive.server.domain.domains.block.domain.QBlock.block;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BlockCustomRepositoryImpl implements BlockCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public boolean queryBlockExistByBlockFromAndBlockUser(Long blockFrom, Long blockUser) {
        Integer fetchOne =
                queryFactory
                        .selectOne()
                        .from(block)
                        .where(blockFromEq(blockFrom), blockUserEq(blockUser))
                        .fetchFirst(); // limit 1
        return fetchOne != null;
    }

    @Override
    public void queryDeleteBlockByBlockFromOrBlockUser(Long userId) {
        queryFactory.delete(block).where(blockUserEqOrBlockFromEq(userId));
    }

    private BooleanExpression blockFromEq(Long blockFrom) {
        return block.blockFrom.eq(blockFrom);
    }

    private BooleanExpression blockUserEq(Long blockUser) {
        return block.blockUser.eq(blockUser);
    }

    private BooleanExpression blockUserEqOrBlockFromEq(Long userId) {
        return blockFromEq(userId).or(blockUserEq(userId));
    }
}
