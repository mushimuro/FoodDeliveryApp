package com.sparta.fooddeliveryapp.domain.like.service;

import com.sparta.fooddeliveryapp.domain.like.dto.UserLikeRequestDto;
import com.sparta.fooddeliveryapp.domain.like.dto.UserLikeResponseDto;
import com.sparta.fooddeliveryapp.domain.like.entity.UserLike;
import com.sparta.fooddeliveryapp.domain.like.entity.UserLikeType;
import com.sparta.fooddeliveryapp.domain.like.repository.UserLikeRepository;
import com.sparta.fooddeliveryapp.domain.review.entity.Review;
import com.sparta.fooddeliveryapp.domain.store.entity.Store;
import com.sparta.fooddeliveryapp.domain.user.entity.User;
import com.sparta.fooddeliveryapp.domain.user.repository.UserRepository;
import com.sparta.fooddeliveryapp.global.error.exception.DuplicateLikeException;
import com.sparta.fooddeliveryapp.global.error.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserLikeService {
    private final UserLikeRepository userLikeRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addUserLike(User user, UserLikeRequestDto userLikeRequestDto) {
        User tempUser = userRepository.findByLoginId(user.getLoginId()).orElseThrow(UserNotFoundException::new);
        if(userLikeRepository.existsByUserAndUserLikeTypeAndTypeId(tempUser, userLikeRequestDto.getUserLikeType(), userLikeRequestDto.getTypeId())){
            throw new DuplicateLikeException("이미 좋아요를 눌렀습니다");
        }
        if(userLikeRequestDto.getUserLikeType().equals(UserLikeType.STORE)){
            tempUser.updateCountStoreLiked();
        }else{
            tempUser.updateCountReviewLiked();
        }

        userLikeRepository.save(
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

    public List<UserLikeResponseDto> getAllUserLikedByType(long userId, UserLikeRequestDto userLikeRequestDto, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        return userLikeRepository.getUserLikeListByUserIdAndTypeWithPageAndSortPriceDesc(userId, userLikeRequestDto.getUserLikeType(), pageRequest.getOffset(), pageRequest.getPageSize())
                .stream()
                .map(m ->
                        UserLikeResponseDto.builder()
                                .userId(m.getUser().getUserId())
                                .userLikeType(m.getUserLikeType())
                                .typeId(m.getTypeId())
                                .build()
                        ).collect(Collectors.toList());
    }

    // QueryDSL
    public List<UserLikeResponseDto> getUserLikeListWithPageAndSortPriceDesc(long userId, int page, int size){

        PageRequest pageRequest = PageRequest.of(page, size);

        return userLikeRepository.getUserLikeListByUserIdWithPageAndSortPriceDesc(userId, pageRequest.getOffset(), pageRequest.getPageSize())
                .stream()
                .map(m ->
                        UserLikeResponseDto.builder()
                                .userId(m.getUser().getUserId())
                                .userLikeType(m.getUserLikeType())
                                .typeId(m.getTypeId())
                                .build()
                ).collect(Collectors.toList());
    }



    public int countStoreLiked(Store store){
        List<UserLike> userLikeList = userLikeRepository.getUserLikeListByUserLikeType(UserLikeType.STORE, store.getStoreId());
        return userLikeList.size();
    }

    public int countReviewLiked(Review review){
        List<UserLike> userLikeList = userLikeRepository.getUserLikeListByUserLikeType(UserLikeType.REVIEW, review.getReviewId());
        return userLikeList.size();
    }
}
