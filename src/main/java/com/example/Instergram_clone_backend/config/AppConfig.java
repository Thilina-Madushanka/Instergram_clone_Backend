    package com.example.Instergram_clone_backend.config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

    @Configuration
    public class AppConfig {
        @Bean
        public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception{

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/signup").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterAfter(new JwtTokenGenerator(), BasicAuthenticationFilter.class)
                    .addFilterBefore(new JwtTokenValidationFilter(), BasicAuthenticationFilter.class)
                    .csrf().disable()
                    .formLogin().and().httpBasic();

                    return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
    }



//package com.example.Instergram_clone_backend.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class AppConfig {
//    @Bean
//    public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Disable CSRF for APIs (enable for web apps)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.POST, "/signup").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(new JwtTokenValidationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JwtTokenGenerator(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
