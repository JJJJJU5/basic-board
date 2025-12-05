package com.example.backend.repository;

import com.example.backend.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AttachmentRepository extends JpaRepository <Attachment,Long> {

}
