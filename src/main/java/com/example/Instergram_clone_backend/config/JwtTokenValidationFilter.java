package com.example.Instergram_clone_backend.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;
public class JwtTokenValidationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt= request.getHeader(SecurityContext.HEADER);

        if(jwt != null && jwt.startsWith("Bearer ")){
            try{
                jwt = jwt.substring(7).trim();
                System.out.println("Received JWT: " + jwt); // Debug log

//                SecretKey key= Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes()); - original
                SecretKey key = SecurityContext.getSigningKey();
//                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody(); - original
//                Claims claims = Jwts.parserBuilder().setSigningKey(SecurityContext.getSigningKey()).build().parseClaimsJws(jwt.substring(7)).getBody();
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();


//                String username = String.valueOf(claims.get("username"));
//                String authorities = (String)claims.get("authorities");
//                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
//                Authentication auth = new UsernamePasswordAuthenticationToken(username, null ,auths);
//                SecurityContextHolder.getContext().setAuthentication(auth);

                String username = claims.get("username", String.class);
                String authorities = claims.get("authorities", String.class);

                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                Authentication auth = new UsernamePasswordAuthenticationToken(username, null, auths);

                SecurityContextHolder.getContext().setAuthentication(auth); // ✅ Fix: Set auth context

//            }catch(Exception e){
//                throw new BadCredentialsException("invalid token...");
//            }

            } catch (io.jsonwebtoken.ExpiredJwtException e) {
                System.err.println("JWT Error: Token has expired.");
                throw new BadCredentialsException("Token has expired.");
            } catch (io.jsonwebtoken.SignatureException e) {
                System.err.println("JWT Error: Invalid token signature.");
                throw new BadCredentialsException("Invalid token signature.");
            } catch (io.jsonwebtoken.io.DecodingException e) {
                System.err.println("JWT Error: Illegal Base64 character.");
                throw new BadCredentialsException("Malformed JWT token.");
            } catch (Exception e) {
                System.err.println("JWT Error: " + e.getMessage());
                throw new BadCredentialsException("Invalid token: " + e.getMessage());
            }

        }
        filterChain.doFilter(request, response);
    }
    protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException{
        return req.getServletPath().equals("/signin");
    }
}

