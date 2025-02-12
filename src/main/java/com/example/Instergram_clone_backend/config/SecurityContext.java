package com.example.Instergram_clone_backend.config;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class SecurityContext {

    public static final String JWT_KEY="fekgnegregkpjgpkgfihgfhgjhuhgfjdhgfjjfdbvjdfbjdfbvjbfdgvdfgjbfgdghjdfghdfkjhglklhkhjhljgkgpkfngeqkfngeprk";
    public static final String HEADER="Authorization";

    public static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
    }

}
