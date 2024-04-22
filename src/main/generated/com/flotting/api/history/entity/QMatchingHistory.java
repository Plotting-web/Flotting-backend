package com.flotting.api.history.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatchingHistory is a Querydsl query type for MatchingHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchingHistory extends EntityPathBase<MatchingHistory> {

    private static final long serialVersionUID = -1669518046L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatchingHistory matchingHistory = new QMatchingHistory("matchingHistory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.flotting.api.util.type.MatchingProcessEnum> matchingProcess = createEnum("matchingProcess", com.flotting.api.util.type.MatchingProcessEnum.class);

    public final QMatchingTicketPurchaseHistory matchingTicketPurchaseHistory;

    public final DateTimePath<java.time.LocalDateTime> receivedAt = createDateTime("receivedAt", java.time.LocalDateTime.class);

    public final com.flotting.api.user.entity.QUserSimpleEntity receiver;

    public final DateTimePath<java.time.LocalDateTime> requestedAt = createDateTime("requestedAt", java.time.LocalDateTime.class);

    public final com.flotting.api.user.entity.QUserSimpleEntity requester;

    public QMatchingHistory(String variable) {
        this(MatchingHistory.class, forVariable(variable), INITS);
    }

    public QMatchingHistory(Path<? extends MatchingHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatchingHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatchingHistory(PathMetadata metadata, PathInits inits) {
        this(MatchingHistory.class, metadata, inits);
    }

    public QMatchingHistory(Class<? extends MatchingHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.matchingTicketPurchaseHistory = inits.isInitialized("matchingTicketPurchaseHistory") ? new QMatchingTicketPurchaseHistory(forProperty("matchingTicketPurchaseHistory"), inits.get("matchingTicketPurchaseHistory")) : null;
        this.receiver = inits.isInitialized("receiver") ? new com.flotting.api.user.entity.QUserSimpleEntity(forProperty("receiver"), inits.get("receiver")) : null;
        this.requester = inits.isInitialized("requester") ? new com.flotting.api.user.entity.QUserSimpleEntity(forProperty("requester"), inits.get("requester")) : null;
    }

}

