package com.example.backend;

import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        // HS256 알고리즘에 맞는 안전한 키를 생성
        SecretKey key = Jwts.SIG.HS256.key().build();
        // 생성된 키를 Base64 문자열로 인코딩
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Generated Base64 Key: " + base64Key);
    }
}