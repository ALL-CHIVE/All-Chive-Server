package allchive.server.domain.domains.user.domain.enums;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFormInfo is a Querydsl query type for FormInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFormInfo extends BeanPath<FormInfo> {

    private static final long serialVersionUID = 1297773007L;

    public static final QFormInfo formInfo = new QFormInfo("formInfo");

    public final StringPath formId = createString("formId");

    public final StringPath formPwd = createString("formPwd");

    public final StringPath phone = createString("phone");

    public QFormInfo(String variable) {
        super(FormInfo.class, forVariable(variable));
    }

    public QFormInfo(Path<? extends FormInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFormInfo(PathMetadata metadata) {
        super(FormInfo.class, metadata);
    }

}

