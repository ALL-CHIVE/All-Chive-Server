package allchive.server.domain.domains.content.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContentTagGroup is a Querydsl query type for ContentTagGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContentTagGroup extends EntityPathBase<ContentTagGroup> {

    private static final long serialVersionUID = 689626147L;

    public static final QContentTagGroup contentTagGroup = new QContentTagGroup("contentTagGroup");

    public final allchive.server.domain.common.model.QBaseTimeEntity _super = new allchive.server.domain.common.model.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QContentTagGroup(String variable) {
        super(ContentTagGroup.class, forVariable(variable));
    }

    public QContentTagGroup(Path<? extends ContentTagGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContentTagGroup(PathMetadata metadata) {
        super(ContentTagGroup.class, metadata);
    }

}

