package com.flotting.api.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QManagerProfileEntity is a Querydsl query type for ManagerProfileEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManagerProfileEntity extends EntityPathBase<ManagerProfileEntity> {

    private static final long serialVersionUID = -1192204435L;

    public static final QManagerProfileEntity managerProfileEntity = new QManagerProfileEntity("managerProfileEntity");

    public final com.flotting.api.util.QBaseEntity _super = new com.flotting.api.util.QBaseEntity(this);

    public final StringPath bankAccount = createString("bankAccount");

    public final StringPath bankName = createString("bankName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    //inherited
    public final NumberPath<Long> seq = _super.seq;

    public QManagerProfileEntity(String variable) {
        super(ManagerProfileEntity.class, forVariable(variable));
    }

    public QManagerProfileEntity(Path<? extends ManagerProfileEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QManagerProfileEntity(PathMetadata metadata) {
        super(ManagerProfileEntity.class, metadata);
    }

}

