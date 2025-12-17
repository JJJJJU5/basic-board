package com.example.backend.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {
    private static final Logger log = LoggerFactory.getLogger(JwtService.class);


    @Value("${jwt.expiration-time:86400000}") // yml에서 값을 가져오거나, 없으면 기본값(24시간) 사용
    private long expirationTime;

    @Value("${jwt.prefix:Bearer }") // yml에서 값을 가져오거나, 없으면 기본값 사용
    private String prefix;

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key;

    @PostConstruct
    public void init() {
        // application.yml에서 주입받은 Base64 인코딩된 secret 값을 디코딩하여 SecretKey를 생성합니다.
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email) {
        return  Jwts.builder()
                .subject(email)
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }
    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            log.warn("JWT token has expired: {}", e.getMessage());
            // 만료된 토큰에서도 사용자 이름은 필요할 수 있으므로, 예외에서 subject를 반환합니다.
            return e.getClaims().getSubject();
        } catch (SignatureException | MalformedJwtException | IllegalArgumentException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            // 유효하지 않은 토큰의 경우 null을 반환하여 필터에서 처리하도록 합니다.
            return null;
        } catch (Exception e) {
            log.error("JWT token parsing error", e);
            return null;
        }
    }
}
