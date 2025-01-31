package com.example.Instergram_clone_backend.controller;

import com.example.Instergram_clone_backend.exceptions.UserException;
import com.example.Instergram_clone_backend.modal.User;
import com.example.Instergram_clone_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private UserService userService;


    @PostMapping("/signUp")
    public ResponseEntity<User> registerUserHandler(@RequestBody User user) throws UserException {
        User createdUser = userService.registerUser(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.OK);
    }

}
