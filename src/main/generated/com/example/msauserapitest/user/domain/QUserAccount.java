package com.example.msauserapitest.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAccount is a Querydsl query type for UserAccount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAccount extends EntityPathBase<UserAccount> {

    private static final long serialVersionUID = -254338844L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAccount userAccount = new QUserAccount("userAccount");

    public final EnumPath<com.example.msauserapitest.user.enums.AccountStatus> accountStatus = createEnum("accountStatus", com.example.msauserapitest.user.enums.AccountStatus.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> createdBy = createNumber("createdBy", Long.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isMarketingAgree = createBoolean("isMarketingAgree");

    public final BooleanPath isPersonalAgree = createBoolean("isPersonalAgree");

    public final BooleanPath isUsePush = createBoolean("isUsePush");

    public final EnumPath<com.example.msauserapitest.user.enums.JoinType> joinType = createEnum("joinType", com.example.msauserapitest.user.enums.JoinType.class);

    public final DateTimePath<java.time.LocalDateTime> lastConnectAt = createDateTime("lastConnectAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = createDateTime("lastModifiedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> lastModifiedBy = createNumber("lastModifiedBy", Long.class);

    public final NumberPath<Short> letGoCount = createNumber("letGoCount", Short.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final QUser user;

    public QUserAccount(String variable) {
        this(UserAccount.class, forVariable(variable), INITS);
    }

    public QUserAccount(Path<? extends UserAccount> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAccount(PathMetadata metadata, PathInits inits) {
        this(UserAccount.class, metadata, inits);
    }

    public QUserAccount(Class<? extends UserAccount> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

