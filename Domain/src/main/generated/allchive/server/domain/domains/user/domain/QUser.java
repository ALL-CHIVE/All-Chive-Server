package allchive.server.domain.domains.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.dsl.PathInits;
import javax.annotation.processing.Generated;

/** QUser is a Querydsl query type for User */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 2080025708L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final allchive.server.domain.common.model.QBaseTimeEntity _super =
            new allchive.server.domain.common.model.QBaseTimeEntity(this);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath Email = createString("Email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastLoginAt =
            createDateTime("lastLoginAt", java.time.LocalDateTime.class);

    public final StringPath nickname = createString("nickname");

    public final allchive.server.domain.domains.user.domain.enums.QOauthInfo oauthInfo;

    public final StringPath profileImgUrl = createString("profileImgUrl");

    // inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final EnumPath<allchive.server.domain.domains.user.domain.enums.UserState> userState =
            createEnum(
                    "userState", allchive.server.domain.domains.user.domain.enums.UserState.class);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.oauthInfo =
                inits.isInitialized("oauthInfo")
                        ? new allchive.server.domain.domains.user.domain.enums.QOauthInfo(
                                forProperty("oauthInfo"))
                        : null;
    }
}
