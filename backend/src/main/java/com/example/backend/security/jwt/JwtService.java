package com.example.backend.security.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {
    static final long EXPIRATION_TIME = 86400000;
    static final String PREFIX = "Bearer ";

    private final SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateToken(String email) {
        String jwt = Jwts.builder()
                .subject(email)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
        return jwt;
    }

    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(token != null && token.startsWith(PREFIX)) {
            try {
                String user = Jwts.parser()
                        .verifyWith(key)    // 비밀 키로 검증
                        .build()
                        .parseSignedClaims(token.replace(PREFIX, "")) // 접두사 "Bearer " 제거
                        .getPayload()
                        .getSubject();

                if (user != null) {
                    return user;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
