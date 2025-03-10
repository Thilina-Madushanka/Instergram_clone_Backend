package com.example.Instergram_clone_backend.repository;

import com.example.Instergram_clone_backend.modal.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer>{


}
