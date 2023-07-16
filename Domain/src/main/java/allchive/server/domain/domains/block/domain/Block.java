package allchive.server.domain.domains.block.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "tbl_block")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Block extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // block 한 사람
    @NotNull private Long blockFrom;

    // Block 당한 유저
    private Long blockUser;

    @Builder
    private Block(Long blockFrom, Long blockUser) {
        this.blockFrom = blockFrom;
        this.blockUser = blockUser;
    }

    public static Block of(Long blockFrom, Long blockUser) {
        return Block.builder().blockFrom(blockFrom).blockUser(blockUser).build();
    }
}
