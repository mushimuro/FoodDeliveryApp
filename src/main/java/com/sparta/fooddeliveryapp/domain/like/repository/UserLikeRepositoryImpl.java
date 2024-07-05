package com.sparta.fooddeliveryapp.domain.like.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.fooddeliveryapp.domain.like.entity.QUserLike;
import com.sparta.fooddeliveryapp.domain.like.entity.UserLike;
import com.sparta.fooddeliveryapp.domain.like.entity.UserLikeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserLikeRepositoryImpl implements UserLikeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserLike> getUserLikeListByUserIdAndTypeWithPageAndSortPriceDesc(long userId, UserLikeType userLikeType, long offset, int pageSize){

        QUserLike userLike = QUserLike.userLike;

        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC, userLike.userLikeId);

        return jpaQueryFactory.selectFrom(userLike)
                .from(userLike)
                .where(userLike.user.userId.eq(userId))
                .where(userLike.userLikeType.eq(userLikeType))
                .offset(offset)
                .limit(pageSize)
                .orderBy(orderSpecifier)
                .fetch();
    }

    @Override
    public List<UserLike> getUserLikeListByUserIdWithPageAndSortPriceDesc(long userId, long offset, int pageSize) {

        QUserLike userLike = QUserLike.userLike;

        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC, userLike.userLikeId);

        return jpaQueryFactory.selectFrom(userLike)
                .from(userLike)
                .where(userLike.user.userId.eq(userId))
                .offset(offset)
                .limit(pageSize)
                .orderBy(orderSpecifier)
                .fetch();
    }

    @Override
    public List<UserLike> getUserLikeListByUserLikeType(UserLikeType userLikeType, long typeId){

        QUserLike userLike = QUserLike.userLike;

        return jpaQueryFactory.selectFrom(userLike)
                .from(userLike)
                .where(userLike.userLikeType.eq(userLikeType))
                .where(userLike.typeId.eq(typeId))
                .fetch();
    }

}
