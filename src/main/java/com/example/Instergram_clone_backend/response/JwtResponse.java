package com.example.Instergram_clone_backend.response;

import com.example.Instergram_clone_backend.modal.User;

public class JwtResponse {

    private String token;
    private User user;
    public JwtResponse(String token, User user) {

        this.token = token;
        this.user = user;

    }

    public User getUser() {
        return user;
    }

}
