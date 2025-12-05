package com.example.backend.repository;

import com.example.backend.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository <PostTag , Long> {
}
