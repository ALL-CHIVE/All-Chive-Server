package allchive.server.domain.domains.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QUserTopicGroup is a Querydsl query type for UserTopicGroup */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTopicGroup extends EntityPathBase<UserTopicGroup> {

    private static final long serialVersionUID = 131338268L;

    public static final QUserTopicGroup userTopicGroup = new QUserTopicGroup("userTopicGroup");

    public final allchive.server.domain.common.model.QBaseTimeEntity _super =
            new allchive.server.domain.common.model.QBaseTimeEntity(this);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QUserTopicGroup(String variable) {
        super(UserTopicGroup.class, forVariable(variable));
    }

    public QUserTopicGroup(Path<? extends UserTopicGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserTopicGroup(PathMetadata metadata) {
        super(UserTopicGroup.class, metadata);
    }
}
