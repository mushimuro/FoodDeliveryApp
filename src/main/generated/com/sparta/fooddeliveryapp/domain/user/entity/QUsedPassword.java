package com.sparta.fooddeliveryapp.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsedPassword is a Querydsl query type for UsedPassword
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsedPassword extends EntityPathBase<UsedPassword> {

    private static final long serialVersionUID = -1982100731L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsedPassword usedPassword = new QUsedPassword("usedPassword");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final QUser user;

    public QUsedPassword(String variable) {
        this(UsedPassword.class, forVariable(variable), INITS);
    }

    public QUsedPassword(Path<? extends UsedPassword> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUsedPassword(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUsedPassword(PathMetadata metadata, PathInits inits) {
        this(UsedPassword.class, metadata, inits);
    }

    public QUsedPassword(Class<? extends UsedPassword> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

