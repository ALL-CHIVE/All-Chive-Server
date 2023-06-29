package allchive.server.domain.domains.block.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QBlock is a Querydsl query type for Block */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlock extends EntityPathBase<Block> {

    private static final long serialVersionUID = 1039614782L;

    public static final QBlock block = new QBlock("block");

    public final allchive.server.domain.common.model.QBaseTimeEntity _super =
            new allchive.server.domain.common.model.QBaseTimeEntity(this);

    public final NumberPath<Long> blockContent = createNumber("blockContent", Long.class);

    public final NumberPath<Long> blockFrom = createNumber("blockFrom", Long.class);

    public final EnumPath<allchive.server.domain.domains.block.domain.enums.BlockType> blockType =
            createEnum(
                    "blockType", allchive.server.domain.domains.block.domain.enums.BlockType.class);

    public final NumberPath<Long> blockUser = createNumber("blockUser", Long.class);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBlock(String variable) {
        super(Block.class, forVariable(variable));
    }

    public QBlock(Path<? extends Block> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlock(PathMetadata metadata) {
        super(Block.class, metadata);
    }
}
