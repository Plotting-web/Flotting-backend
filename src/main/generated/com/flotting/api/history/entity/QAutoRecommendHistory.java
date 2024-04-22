package com.flotting.api.history.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAutoRecommendHistory is a Querydsl query type for AutoRecommendHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAutoRecommendHistory extends EntityPathBase<AutoRecommendHistory> {

    private static final long serialVersionUID = 40974236L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAutoRecommendHistory autoRecommendHistory = new QAutoRecommendHistory("autoRecommendHistory");

    public final com.flotting.api.util.QBaseEntity _super = new com.flotting.api.util.QBaseEntity(this);

    public final EnumPath<com.flotting.api.util.type.AutoRecommendProcessEnum> autoRecommendProcessEnum = createEnum("autoRecommendProcessEnum", com.flotting.api.util.type.AutoRecommendProcessEnum.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.flotting.api.user.entity.QUserSimpleEntity profile;

    public final com.flotting.api.user.entity.QUserSimpleEntity receiver;

    //inherited
    public final NumberPath<Long> seq = _super.seq;

    public QAutoRecommendHistory(String variable) {
        this(AutoRecommendHistory.class, forVariable(variable), INITS);
    }

    public QAutoRecommendHistory(Path<? extends AutoRecommendHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAutoRecommendHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAutoRecommendHistory(PathMetadata metadata, PathInits inits) {
        this(AutoRecommendHistory.class, metadata, inits);
    }

    public QAutoRecommendHistory(Class<? extends AutoRecommendHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.profile = inits.isInitialized("profile") ? new com.flotting.api.user.entity.QUserSimpleEntity(forProperty("profile"), inits.get("profile")) : null;
        this.receiver = inits.isInitialized("receiver") ? new com.flotting.api.user.entity.QUserSimpleEntity(forProperty("receiver"), inits.get("receiver")) : null;
    }

}

