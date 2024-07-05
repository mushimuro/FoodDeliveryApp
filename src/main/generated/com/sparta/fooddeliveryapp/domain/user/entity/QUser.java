package com.sparta.fooddeliveryapp.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1721412008L;

    public static final QUser user = new QUser("user");

    public final com.sparta.fooddeliveryapp.global.common.QTimeStamped _super = new com.sparta.fooddeliveryapp.global.common.QTimeStamped(this);

    public final StringPath address = createString("address");

    public final NumberPath<Integer> countReviewLiked = createNumber("countReviewLiked", Integer.class);

    public final NumberPath<Integer> countStoreLiked = createNumber("countStoreLiked", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final StringPath intro = createString("intro");

    public final NumberPath<Long> kakaoId = createNumber("kakaoId", Long.class);

    public final StringPath loginId = createString("loginId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath refreshToken = createString("refreshToken");

    public final EnumPath<UserRoleEnum> role = createEnum("role", UserRoleEnum.class);

    public final EnumPath<UserStatusEnum> status = createEnum("status", UserStatusEnum.class);

    public final ListPath<UsedPassword, QUsedPassword> usedPasswordList = this.<UsedPassword, QUsedPassword>createList("usedPasswordList", UsedPassword.class, QUsedPassword.class, PathInits.DIRECT2);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

