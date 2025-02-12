package com.example.Instergram_clone_backend.controller;

import com.example.Instergram_clone_backend.config.SecurityContext;
import com.example.Instergram_clone_backend.exceptions.UserException;
import com.example.Instergram_clone_backend.modal.User;
import com.example.Instergram_clone_backend.repository.UserRepository;
import com.example.Instergram_clone_backend.response.JwtResponse;
import com.example.Instergram_clone_backend.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;
@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepo;

    @PostMapping("/signup")
    public ResponseEntity<User> registerUserHandler(@RequestBody User user) throws UserException {
        User createdUser = userService.registerUser(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.OK);
    }

    @GetMapping("/signin")
    public ResponseEntity<User> signinHandler(Authentication auth) throws BadCredentialsException{
        Optional<User> opt = userRepo.findByEmail(auth.getName());
        if(opt.isPresent()){
//            return new ResponseEntity<User>(opt.get(),HttpStatus.ACCEPTED);
//            String jwtToken = userService.generateJwtToken(opt.get()); // Ensure this method exists in UserService
//            return ResponseEntity.ok(new JwtResponse(jwtToken, opt.get()));

            String jwtToken = Jwts.builder()
                    .setIssuer("InstagramClone")
                    .setIssuedAt(new Date())
                    .claim("username", auth.getName())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                    .signWith(SecurityContext.getSigningKey())
                    .compact();

//            return ResponseEntity.ok(new JwtResponse("Bearer " + jwtToken, opt.get()));
            return ResponseEntity.ok(new JwtResponse( "Bearer " + jwtToken, opt.get()).getUser());

        }
        throw new BadCredentialsException("Invalid username or password");
    }

}
