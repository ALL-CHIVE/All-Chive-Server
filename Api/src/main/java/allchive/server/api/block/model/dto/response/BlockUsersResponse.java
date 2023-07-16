package allchive.server.api.block.model.dto.response;

import allchive.server.api.block.model.vo.BlockUserVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BlockUsersResponse {
    List<BlockUserVo> users;

    @Builder
    private BlockUsersResponse(List<BlockUserVo> users) {
        this.users = users;
    }

    public static BlockUsersResponse from(List<BlockUserVo> users) {
        return BlockUsersResponse.builder()
                .users(users)
                .build();
    }
}
