package com.flotting.api.history.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCSHistory is a Querydsl query type for CSHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCSHistory extends EntityPathBase<CSHistory> {

    private static final long serialVersionUID = -954700369L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCSHistory cSHistory = new QCSHistory("cSHistory");

    public final StringPath content = createString("content");

    public final EnumPath<com.flotting.api.user.enums.CSEnum> csType = createEnum("csType", com.flotting.api.user.enums.CSEnum.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.flotting.api.manager.entity.QManagerProfileEntity manager;

    public final StringPath managerComment = createString("managerComment");

    public final DatePath<java.time.LocalDate> requestDate = createDate("requestDate", java.time.LocalDate.class);

    public final com.flotting.api.user.entity.QUserSimpleEntity requester;

    public final EnumPath<com.flotting.api.user.enums.ProcessStatus> status = createEnum("status", com.flotting.api.user.enums.ProcessStatus.class);

    public QCSHistory(String variable) {
        this(CSHistory.class, forVariable(variable), INITS);
    }

    public QCSHistory(Path<? extends CSHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCSHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCSHistory(PathMetadata metadata, PathInits inits) {
        this(CSHistory.class, metadata, inits);
    }

    public QCSHistory(Class<? extends CSHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.manager = inits.isInitialized("manager") ? new com.flotting.api.manager.entity.QManagerProfileEntity(forProperty("manager")) : null;
        this.requester = inits.isInitialized("requester") ? new com.flotting.api.user.entity.QUserSimpleEntity(forProperty("requester"), inits.get("requester")) : null;
    }

}

