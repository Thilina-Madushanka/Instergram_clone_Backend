package com.example.Instergram_clone_backend.config;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenGenerator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.isAuthenticated()){
//            SecretKey key= Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
            SecretKey key = SecurityContext.getSigningKey();
            String jwt= Jwts.builder()
//                    .setIssuer("Instergram")
//                    .setIssuedAt(new Date())
//                    .claim("authorities",populateAuthorities(authentication.getAuthorities()))
//                    .claim("username",authentication.getName())
//                    .setExpiration(new Date(new Date().getTime() + 300000000))
//                    .signWith(key).compact();

                    .setIssuer("Instergram")
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .claim("username", authentication.getName())
                    .signWith(key)
//                    .signWith(SecurityContext.getSigningKey())
                    .compact();

//            response.setHeader(SecurityContext.HEADER, jwt);
            response.setHeader(SecurityContext.HEADER, "Bearer " + jwt);
        }
        filterChain.doFilter(request, response);
    }
    public String populateAuthorities(Collection<? extends GrantedAuthority> collection){
        Set<String> authorities = new HashSet<>();
        for(GrantedAuthority authority: collection){
            authorities.add(authority.getAuthority());
        }
        return String.join(",",authorities);
    }

//    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        return authorities.stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//    }

    protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException{
        return req.getServletPath().equals("/signin");
    }
}

