package com.example.Instergram_clone_backend.service;

import com.example.Instergram_clone_backend.exceptions.UserException;
import com.example.Instergram_clone_backend.modal.User;

import java.util.List;

public class UserServiceImplementation implements UserService {

    @Override
    public User registerUser(User user) throws UserException {
        return null;
    }

    @Override
    public User findUserById(Integer userId) throws UserException {
        return null;
    }

    @Override
    public User findUserProfile(String token) throws UserException {
        return null;
    }

    @Override
    public User findUserByUsername(String username) throws UserException {
        return null;
    }

    @Override
    public String followUser(Integer reqUserId, Integer followUserId) throws UserException {
        return null;
    }

    @Override
    public String unFollowUser(Integer reqUserId, Integer followUserId) throws UserException {
        return null;
    }

    @Override
    public List<User> findUserByIds(List<Integer> userIds) throws UserException {
        return null;
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        return null;
    }

    @Override
    public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
        return null;
    }
}
