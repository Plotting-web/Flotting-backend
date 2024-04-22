package com.flotting.api.user.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserProfileEntity is a Querydsl query type for UserProfileEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserProfileEntity extends EntityPathBase<UserProfileEntity> {

    private static final long serialVersionUID = -1226440715L;

    public static final QUserProfileEntity userProfileEntity = new QUserProfileEntity("userProfileEntity");

    public final NumberPath<Long> age = createNumber("age", Long.class);

    public final StringPath email = createString("email");

    public final StringPath job = createString("job");

    public final NumberPath<Long> phoneNumber = createNumber("phoneNumber", Long.class);

    public final StringPath userName = createString("userName");

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final StringPath userStatus = createString("userStatus");

    public QUserProfileEntity(String variable) {
        super(UserProfileEntity.class, forVariable(variable));
    }

    public QUserProfileEntity(Path<? extends UserProfileEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserProfileEntity(PathMetadata metadata) {
        super(UserProfileEntity.class, metadata);
    }

}

