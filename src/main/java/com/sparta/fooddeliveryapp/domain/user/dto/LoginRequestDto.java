package com.sparta.fooddeliveryapp.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String loginId;
    private String password;
}
