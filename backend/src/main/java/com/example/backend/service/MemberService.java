package com.example.backend.service;


import com.example.backend.entity.Member;
import com.example.backend.entity.Role;

import com.example.backend.exception.DuplicateEmailException;
import com.example.backend.exception.MemberNotFoundException;
import com.example.backend.record.LoginRequest;
import com.example.backend.record.LoginResult;
import com.example.backend.record.SignUpRequest;
import com.example.backend.repository.MemberRepository;
import com.example.backend.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public void signUp(SignUpRequest request) {
        // 1. 이메일 중복 검사
        if (memberRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException("이미 가입된 이메일입니다.");
        }
        // 2. 비밀번호 암호화 및 회원 엔티티 생성
        Member member = Member.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .nickname(request.nickname())
                .role(Role.USER)
                .build();

        // 3. 저장
        memberRepository.save(member);
    }

    public LoginResult login(LoginRequest request) {
        // 1. 인증 수행 (실패 시 AuthenticationException 발생)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        // 2. 회원 정보 조회 (화면 표시용)
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberNotFoundException("가입되지 않은 이메일입니다."));

        // 3. 토큰 생성 및 결과 반환
        String token = jwtService.generateToken(request.email());
        return new LoginResult(token, member);
    }
}