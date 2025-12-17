package com.example.backend.record;

public record LoginResponse(
        String email,
        String nickname,
        String role
) {
}