package com.example.Instergram_clone_backend.repository;

import com.example.Instergram_clone_backend.modal.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from post p where p.user.id=?1")
    public List<Post> findUserById(Integer userId);
    @Query("select p from post p where p.user.id IN : users ORDER BY p.createdAt DESC")
    public List<Post> findAllPostByUserIds(@Param("users") List<Integer> userIds);
}
