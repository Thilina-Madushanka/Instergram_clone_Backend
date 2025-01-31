package com.example.Instergram_clone_backend.repository;

import com.example.Instergram_clone_backend.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
    public Optional<User> findByUsername(String username);
    @Query("SELECT u From User u Where u.id IN :users")
    public List<User> findAllUsersByUserIds(@Param("users") List<Integer> userIds);
    @Query("SELECT DISTINCT u From User u Where u.username LIKE %:query% OR u.email LIKE %:query%")
    public List<User> findByQuery(@Param("query") String query);
}
