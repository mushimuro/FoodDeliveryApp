package com.sparta.fooddeliveryapp.domain.like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLike is a Querydsl query type for UserLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLike extends EntityPathBase<UserLike> {

    private static final long serialVersionUID = 1966684635L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLike userLike = new QUserLike("userLike");

    public final NumberPath<Long> typeId = createNumber("typeId", Long.class);

    public final com.sparta.fooddeliveryapp.domain.user.entity.QUser user;

    public final NumberPath<Long> userLikeId = createNumber("userLikeId", Long.class);

    public final EnumPath<UserLikeType> userLikeType = createEnum("userLikeType", UserLikeType.class);

    public QUserLike(String variable) {
        this(UserLike.class, forVariable(variable), INITS);
    }

    public QUserLike(Path<? extends UserLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLike(PathMetadata metadata, PathInits inits) {
        this(UserLike.class, metadata, inits);
    }

    public QUserLike(Class<? extends UserLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.sparta.fooddeliveryapp.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

