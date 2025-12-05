package com.example.backend.entity;

import jakarta.persistence.*;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "original_name",nullable = true)
    private String originalName;

    @Column(name = "save_name",nullable = true)
    private String saveName;

    @Column(name = "file_path",nullable = true)
    private String filePath;
}
