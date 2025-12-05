package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false ,updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "current_point",nullable = false)
    private int currentPoint = 0;



}
