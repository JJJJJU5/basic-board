package com.example.backend.controller;

import com.example.backend.record.LoginRequest;
import com.example.backend.record.LoginResponse;
import com.example.backend.record.LoginResult;
import com.example.backend.record.SignUpRequest;
import com.example.backend.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Value("${cookie.secure:true}") // application.yml에서 값을 읽어오고, 없으면 true를 기본값으로 사용
    private boolean cookieSecure;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignUpRequest request) {
        memberService.signUp(request);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        LoginResult loginResult = memberService.login(request);

        ResponseCookie cookie = ResponseCookie.from("accessToken", loginResult.token())
                .httpOnly(true) //JS 접근 불가
                .secure(cookieSecure)  // 프로필에 따라 동적으로 설정
                .path("/") // 모든 경로에서 유효
                .maxAge(60 * 60 * 24) // 24시간 유효
                .sameSite("Strict")  // CSRF 방지
                .build();
        // 헤더에 쿠키 심기
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        // 바디에는 토큰 제외하고 회원 정보만 리턴
        return ResponseEntity.ok(new LoginResponse(
                loginResult.member().getEmail(),
                loginResult.member().getNickname(),
                loginResult.member().getRole().name()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0) // 유효 시간을 0으로 설정하여 즉시 삭제
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }
}