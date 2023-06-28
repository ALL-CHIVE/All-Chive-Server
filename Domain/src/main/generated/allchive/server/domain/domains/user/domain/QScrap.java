package allchive.server.domain.domains.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QScrap is a Querydsl query type for Scrap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScrap extends EntityPathBase<Scrap> {

    private static final long serialVersionUID = 53975888L;

    public static final QScrap scrap = new QScrap("scrap");

    public final allchive.server.domain.common.model.QBaseTimeEntity _super = new allchive.server.domain.common.model.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QScrap(String variable) {
        super(Scrap.class, forVariable(variable));
    }

    public QScrap(Path<? extends Scrap> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScrap(PathMetadata metadata) {
        super(Scrap.class, metadata);
    }

}

