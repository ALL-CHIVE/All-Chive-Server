package allchive.server.domain.domains.block.repository;

public interface BlockCustomRepository {
    boolean queryBlockExistByBlockFromAndBlockUser(Long blockFrom, Long blockUser);
}
