package com.sparta.fooddeliveryapp.domain.like.repository;

import com.sparta.fooddeliveryapp.domain.like.entity.UserLike;
import com.sparta.fooddeliveryapp.domain.like.entity.UserLikeType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLikeRepositoryCustom {

    List<UserLike> getUserLikeListByUserIdAndTypeWithPageAndSortPriceDesc(long userId, UserLikeType userLikeType, long offset, int pageSize);

    List<UserLike> getUserLikeListByUserIdWithPageAndSortPriceDesc(long userId, long offset, int pageSize);

    List<UserLike> getUserLikeListByUserLikeType(UserLikeType userLikeType, long typeId);
}
