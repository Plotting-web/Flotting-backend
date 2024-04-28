package com.flotting.api.history.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatchingTicketPurchaseHistory is a Querydsl query type for MatchingTicketPurchaseHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchingTicketPurchaseHistory extends EntityPathBase<MatchingTicketPurchaseHistory> {

    private static final long serialVersionUID = -1300066379L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatchingTicketPurchaseHistory matchingTicketPurchaseHistory = new QMatchingTicketPurchaseHistory("matchingTicketPurchaseHistory");

    public final com.flotting.api.util.QBaseEntity _super = new com.flotting.api.util.QBaseEntity(this);

    public final StringPath comment = createString("comment");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QMatchingHistory matchingHistory;

    public final DateTimePath<java.time.LocalDateTime> paidAt = createDateTime("paidAt", java.time.LocalDateTime.class);

    public final StringPath price = createString("price");

    //inherited
    public final NumberPath<Long> seq = _super.seq;

    public final StringPath ticketName = createString("ticketName");

    public final DateTimePath<java.time.LocalDateTime> usedAt = createDateTime("usedAt", java.time.LocalDateTime.class);

    public final com.flotting.api.user.entity.QUserSimpleEntity userSimpleEntity;

    public QMatchingTicketPurchaseHistory(String variable) {
        this(MatchingTicketPurchaseHistory.class, forVariable(variable), INITS);
    }

    public QMatchingTicketPurchaseHistory(Path<? extends MatchingTicketPurchaseHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatchingTicketPurchaseHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatchingTicketPurchaseHistory(PathMetadata metadata, PathInits inits) {
        this(MatchingTicketPurchaseHistory.class, metadata, inits);
    }

    public QMatchingTicketPurchaseHistory(Class<? extends MatchingTicketPurchaseHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.matchingHistory = inits.isInitialized("matchingHistory") ? new QMatchingHistory(forProperty("matchingHistory"), inits.get("matchingHistory")) : null;
        this.userSimpleEntity = inits.isInitialized("userSimpleEntity") ? new com.flotting.api.user.entity.QUserSimpleEntity(forProperty("userSimpleEntity"), inits.get("userSimpleEntity")) : null;
    }

}

