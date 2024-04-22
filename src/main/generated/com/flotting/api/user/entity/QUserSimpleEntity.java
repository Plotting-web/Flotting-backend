package com.flotting.api.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserSimpleEntity is a Querydsl query type for UserSimpleEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserSimpleEntity extends EntityPathBase<UserSimpleEntity> {

    private static final long serialVersionUID = 19333412L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserSimpleEntity userSimpleEntity = new QUserSimpleEntity("userSimpleEntity");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final QUserDetailEntity userDetailEntity;

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public QUserSimpleEntity(String variable) {
        this(UserSimpleEntity.class, forVariable(variable), INITS);
    }

    public QUserSimpleEntity(Path<? extends UserSimpleEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserSimpleEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserSimpleEntity(PathMetadata metadata, PathInits inits) {
        this(UserSimpleEntity.class, metadata, inits);
    }

    public QUserSimpleEntity(Class<? extends UserSimpleEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userDetailEntity = inits.isInitialized("userDetailEntity") ? new QUserDetailEntity(forProperty("userDetailEntity"), inits.get("userDetailEntity")) : null;
    }

}

