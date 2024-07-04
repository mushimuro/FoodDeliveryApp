package com.sparta.fooddeliveryapp.domain.like.repository;

import com.sparta.fooddeliveryapp.domain.like.entity.UserLike;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLikeRepositoryCustom {

    List<UserLike> getUserLikeListWithPageAndSortPriceDesc(long userId, long offset, int pageSize);
}
