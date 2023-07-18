package allchive.server.api.block.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BlockUserVo {
    @Schema(defaultValue = "닉네임", description = "차단한 유저의 닉네임")
    private String nickname;

    @Schema(defaultValue = "1", description = "차단한 유저의 고유번호")
    private Long id;

    @Builder
    private BlockUserVo(String nickname, Long id) {
        this.nickname = nickname;
        this.id = id;
    }

    public static BlockUserVo of(String nickname, Long id) {
        return BlockUserVo.builder().nickname(nickname).id(id).build();
    }
}
