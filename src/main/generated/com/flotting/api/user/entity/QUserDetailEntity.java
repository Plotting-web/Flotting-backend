package com.flotting.api.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserDetailEntity is a Querydsl query type for UserDetailEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserDetailEntity extends EntityPathBase<UserDetailEntity> {

    private static final long serialVersionUID = 1926444163L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserDetailEntity userDetailEntity = new QUserDetailEntity("userDetailEntity");

    public final com.flotting.api.util.QBaseEntity _super = new com.flotting.api.util.QBaseEntity(this);

    public final EnumPath<com.flotting.api.user.enums.AppliedPathEnum> appliedPath = createEnum("appliedPath", com.flotting.api.user.enums.AppliedPathEnum.class);

    public final DateTimePath<java.time.LocalDateTime> approvedAt = createDateTime("approvedAt", java.time.LocalDateTime.class);

    public final StringPath birthday = createString("birthday");

    public final NumberPath<Integer> birthYear = createNumber("birthYear", Integer.class);

    public final ListPath<com.flotting.api.user.enums.CharacterEnum, EnumPath<com.flotting.api.user.enums.CharacterEnum>> character = this.<com.flotting.api.user.enums.CharacterEnum, EnumPath<com.flotting.api.user.enums.CharacterEnum>>createList("character", com.flotting.api.user.enums.CharacterEnum.class, EnumPath.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath detailJob = createString("detailJob");

    public final StringPath detailLocation = createString("detailLocation");

    public final EnumPath<com.flotting.api.user.enums.DrinkingEnum> drinking = createEnum("drinking", com.flotting.api.user.enums.DrinkingEnum.class);

    public final EnumPath<com.flotting.api.user.enums.EducationEnum> education = createEnum("education", com.flotting.api.user.enums.EducationEnum.class);

    public final StringPath email = createString("email");

    public final EnumPath<com.flotting.api.user.enums.GenderEnum> gender = createEnum("gender", com.flotting.api.user.enums.GenderEnum.class);

    public final EnumPath<com.flotting.api.user.enums.GradeEnum> grade = createEnum("grade", com.flotting.api.user.enums.GradeEnum.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final ListPath<com.flotting.api.user.enums.HobbyEnum, EnumPath<com.flotting.api.user.enums.HobbyEnum>> hobby = this.<com.flotting.api.user.enums.HobbyEnum, EnumPath<com.flotting.api.user.enums.HobbyEnum>>createList("hobby", com.flotting.api.user.enums.HobbyEnum.class, EnumPath.class, PathInits.DIRECT2);

    public final StringPath identityVerificationURL = createString("identityVerificationURL");

    public final EnumPath<com.flotting.api.user.enums.JobEnum> job = createEnum("job", com.flotting.api.user.enums.JobEnum.class);

    public final StringPath lifeStyle = createString("lifeStyle");

    public final EnumPath<com.flotting.api.user.enums.LocationEnum> location = createEnum("location", com.flotting.api.user.enums.LocationEnum.class);

    public final com.flotting.api.manager.entity.QManagerProfileEntity manager;

    public final StringPath managerComment = createString("managerComment");

    public final StringPath mbti = createString("mbti");

    public final StringPath nickName = createString("nickName");

    public final StringPath preferredDate = createString("preferredDate");

    public final ListPath<String, StringPath> profileImageURLs = this.<String, StringPath>createList("profileImageURLs", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath recommendUserName = createString("recommendUserName");

    public final StringPath rejectedReason = createString("rejectedReason");

    //inherited
    public final NumberPath<Long> seq = _super.seq;

    public final BooleanPath smoking = createBoolean("smoking");

    public final StringPath somethingWantToSay = createString("somethingWantToSay");

    public final QUserSimpleEntity userSimpleEntity;

    public final EnumPath<com.flotting.api.user.enums.UserStatusEnum> userStatus = createEnum("userStatus", com.flotting.api.user.enums.UserStatusEnum.class);

    public QUserDetailEntity(String variable) {
        this(UserDetailEntity.class, forVariable(variable), INITS);
    }

    public QUserDetailEntity(Path<? extends UserDetailEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserDetailEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserDetailEntity(PathMetadata metadata, PathInits inits) {
        this(UserDetailEntity.class, metadata, inits);
    }

    public QUserDetailEntity(Class<? extends UserDetailEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.manager = inits.isInitialized("manager") ? new com.flotting.api.manager.entity.QManagerProfileEntity(forProperty("manager")) : null;
        this.userSimpleEntity = inits.isInitialized("userSimpleEntity") ? new QUserSimpleEntity(forProperty("userSimpleEntity"), inits.get("userSimpleEntity")) : null;
    }

}

