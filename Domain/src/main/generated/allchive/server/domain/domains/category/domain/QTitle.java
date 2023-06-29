package allchive.server.domain.domains.category.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QTitle is a Querydsl query type for Title */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTitle extends EntityPathBase<Title> {

    private static final long serialVersionUID = -1270219644L;

    public static final QTitle title = new QTitle("title");

    public final allchive.server.domain.common.model.QBaseTimeEntity _super =
            new allchive.server.domain.common.model.QBaseTimeEntity(this);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTitle(String variable) {
        super(Title.class, forVariable(variable));
    }

    public QTitle(Path<? extends Title> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTitle(PathMetadata metadata) {
        super(Title.class, metadata);
    }
}
