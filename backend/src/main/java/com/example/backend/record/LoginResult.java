package com.example.backend.record;

import com.example.backend.entity.Member;

public record LoginResult(String token, Member member) {
}