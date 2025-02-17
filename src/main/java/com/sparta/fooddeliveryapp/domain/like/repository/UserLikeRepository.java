package com.sparta.fooddeliveryapp.domain.like.repository;

import com.sparta.fooddeliveryapp.domain.like.entity.UserLike;
import com.sparta.fooddeliveryapp.domain.like.entity.UserLikeType;
import com.sparta.fooddeliveryapp.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLikeRepository extends JpaRepository<UserLike, Long>, UserLikeRepositoryCustom {

    boolean existsByUserAndUserLikeTypeAndTypeId(User user, UserLikeType userLikeType, Long typeId);

    Optional<UserLike> findByUserAndUserLikeTypeAndTypeId(User user, UserLikeType userLikeType, Long typeId);
}
