package com.sparta.fooddeliveryapp.domain.like.service;

import com.sparta.fooddeliveryapp.domain.like.dto.UserLikeRequestDto;
import com.sparta.fooddeliveryapp.domain.like.dto.UserLikeResponseDto;
import com.sparta.fooddeliveryapp.domain.like.entity.UserLike;
import com.sparta.fooddeliveryapp.domain.like.entity.UserLikeType;
import com.sparta.fooddeliveryapp.domain.like.repository.UserLikeRepository;
import com.sparta.fooddeliveryapp.domain.order.dto.OrderDetailResponseDto;
import com.sparta.fooddeliveryapp.domain.order.dto.OrderResponseDto;
import com.sparta.fooddeliveryapp.domain.order.entity.OrderDetail;
import com.sparta.fooddeliveryapp.domain.order.entity.Orders;
import com.sparta.fooddeliveryapp.domain.review.entity.Review;
import com.sparta.fooddeliveryapp.domain.store.entity.Store;
import com.sparta.fooddeliveryapp.domain.user.entity.User;
import com.sparta.fooddeliveryapp.domain.user.repository.UserRepository;
import com.sparta.fooddeliveryapp.global.error.exception.DuplicateLikeException;
import com.sparta.fooddeliveryapp.global.error.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLikeService {
    private final UserLikeRepository userLikeRepository;
    private final UserRepository userRepository;

    @Transactional
    public UserLike addUserLike(User user, UserLikeRequestDto userLikeRequestDto) {
        User tempUser = userRepository.findByLoginId(user.getLoginId()).orElseThrow(UserNotFoundException::new);
        if(userLikeRepository.existsByUserAndUserLikeTypeAndTypeId(tempUser, userLikeRequestDto.getUserLikeType(), userLikeRequestDto.getTypeId())){
            throw new DuplicateLikeException("이미 좋아요를 눌렀습니다");
        }
        if(userLikeRequestDto.getUserLikeType().equals(UserLikeType.STORE)){
            tempUser.updateCountStoreLiked();
        }else{
            tempUser.updateCountReviewLiked();
        }

        return userLikeRepository.save(
                UserLike.builder()
                        .user(tempUser)
                        .userLikeType(userLikeRequestDto.getUserLikeType())
                        .typeId(userLikeRequestDto.getTypeId())
                        .build());
    }

    public void deleteUserLike(User user, UserLikeRequestDto userLikeRequestDto) {
        // 사용자 좋아요 취소 _ 데이터 좋아요 상태인지 확인, 본인확인,
        UserLike userLike = userLikeRepository.findByUserAndUserLikeTypeAndTypeId(user, userLikeRequestDto.getUserLikeType(), userLikeRequestDto.getTypeId()).orElseThrow(
                () -> new NullPointerException("취소할 좋아요가 없습니다")
        );
        userLikeRepository.delete(userLike);
    }

    public List<UserLikeResponseDto> getUserLike(UserLikeRequestDto userLikeRequestDto) {
         List<UserLike> userLikeList = userLikeRepository.findAllByUserLikeTypeAndTypeId(userLikeRequestDto.getUserLikeType(), userLikeRequestDto.getTypeId()).orElseThrow(
                () -> new NullPointerException("등록된 좋아요가 없습니다")
        );
        return userLikeList.stream().map(
                userLike -> UserLikeResponseDto.builder()
                        .userId(userLike.getUser().getUserId())
                        .userLikeType(userLike.getUserLikeType())
                        .typeId(userLike.getTypeId())
                        .build()
        ).toList();
    }

    public List<UserLikeResponseDto> getAllUserLiked(UserLikeRequestDto userLikeRequestDto, User user, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserLike> userLikePage = userLikeRepository.findAllByUserLikeTypeAndUserUserIdOrderByTypeIdDesc(userLikeRequestDto.getUserLikeType(), user.getUserId(), pageable).orElseThrow(
                () -> new NullPointerException("등록된 좋아요가 없습니다")
        );

        return userLikePage.stream().map(
                userLike -> UserLikeResponseDto.builder()
                        .userId(userLike.getUser().getUserId())
                        .userLikeType(userLike.getUserLikeType())
                        .typeId(userLike.getTypeId())
                        .build()
        ).toList();
    }

    public int countStoreLiked(Store store){
        List<UserLike> userLikeList = userLikeRepository.findAllByUserLikeTypeAndTypeId(UserLikeType.STORE, store.getStoreId()).orElseThrow(
                () -> new NullPointerException("조회된 매장이 없습니다")
        );
        return userLikeList.size();
    }

    public int countReviewLiked(Review review){
        List<UserLike> userLikeList = userLikeRepository.findAllByUserLikeTypeAndTypeId(UserLikeType.REVIEW, review.getReviewId()).orElseThrow(
                () -> new NullPointerException("조회된 리뷰가 없습니다")
        );
        return userLikeList.size();
    }
}
