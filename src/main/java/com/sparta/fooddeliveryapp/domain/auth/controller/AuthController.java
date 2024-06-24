package com.sparta.fooddeliveryapp.domain.auth.controller;

import com.sparta.fooddeliveryapp.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<String> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
        return ResponseEntity.status(HttpStatus.OK).body("토큰이 새로 발급되었습니다.");
    }

    @GetMapping("/auth/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.OK).body("응답 테스트 성공");
    }

    @GetMapping("/nonAuth/test")
    public ResponseEntity<String> tokenTest() {
        return ResponseEntity.status(HttpStatus.OK).body("응답 테스트 성공");
    }
}
