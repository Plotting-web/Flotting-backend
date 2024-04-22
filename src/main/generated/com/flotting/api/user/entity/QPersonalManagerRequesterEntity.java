package com.flotting.api.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPersonalManagerRequesterEntity is a Querydsl query type for PersonalManagerRequesterEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonalManagerRequesterEntity extends EntityPathBase<PersonalManagerRequesterEntity> {

    private static final long serialVersionUID = -9837578L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonalManagerRequesterEntity personalManagerRequesterEntity = new QPersonalManagerRequesterEntity("personalManagerRequesterEntity");

    public final com.flotting.api.util.QBaseEntity _super = new com.flotting.api.util.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.flotting.api.manager.entity.QManagerProfileEntity manager;

    public final StringPath managerLetter = createString("managerLetter");

    public final QUserSimpleEntity requester;

    public final StringPath requesterLetter = createString("requesterLetter");

    //inherited
    public final NumberPath<Long> seq = _super.seq;

    public QPersonalManagerRequesterEntity(String variable) {
        this(PersonalManagerRequesterEntity.class, forVariable(variable), INITS);
    }

    public QPersonalManagerRequesterEntity(Path<? extends PersonalManagerRequesterEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPersonalManagerRequesterEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPersonalManagerRequesterEntity(PathMetadata metadata, PathInits inits) {
        this(PersonalManagerRequesterEntity.class, metadata, inits);
    }

    public QPersonalManagerRequesterEntity(Class<? extends PersonalManagerRequesterEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.manager = inits.isInitialized("manager") ? new com.flotting.api.manager.entity.QManagerProfileEntity(forProperty("manager")) : null;
        this.requester = inits.isInitialized("requester") ? new QUserSimpleEntity(forProperty("requester"), inits.get("requester")) : null;
    }

}

