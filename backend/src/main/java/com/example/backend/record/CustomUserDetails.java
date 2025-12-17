package com.example.backend.record;

import com.example.backend.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public record CustomUserDetails(Member member) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Member 엔티티의 Role 정보를 기반으로 권한을 생성
        // Spring Security는 역할 이름 앞에 "ROLE_" 접두사가 붙는 것을 기본
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + member.getRole().name()));
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        // 인증의 주체로 사용할 필드를 반환. (여기서는 이메일)
        return member.getEmail();
    }

    // 계정 상태 관련 메소드 (만료, 잠김 등) - 지금은 모두 true로 설정.
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}