package allchive.server.domain.domains.recycle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QRecycle is a Querydsl query type for Recycle */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecycle extends EntityPathBase<Recycle> {

    private static final long serialVersionUID = -1015076930L;

    public static final QRecycle recycle = new QRecycle("recycle");

    public final allchive.server.domain.common.model.QBaseTimeEntity _super =
            new allchive.server.domain.common.model.QBaseTimeEntity(this);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QRecycle(String variable) {
        super(Recycle.class, forVariable(variable));
    }

    public QRecycle(Path<? extends Recycle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecycle(PathMetadata metadata) {
        super(Recycle.class, metadata);
    }
}
