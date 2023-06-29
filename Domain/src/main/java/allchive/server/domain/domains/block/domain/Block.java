package allchive.server.domain.domains.block.domain;


import allchive.server.domain.common.model.BaseTimeEntity;
import allchive.server.domain.domains.block.domain.enums.BlockType;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
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

    @Enumerated(EnumType.STRING)
    private BlockType blockType;

    // Block 당한 유저
    private Long blockUser;

    // Block 당한 컨텐츠
    private Long blockContent;
}
