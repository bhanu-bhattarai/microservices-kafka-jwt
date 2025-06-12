package org.example.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jwt.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .addFilterBefore((req, res, chain) -> {
                    HttpServletRequest request = (HttpServletRequest) req;
                    HttpServletResponse response = (HttpServletResponse) res;

                    String authHeader = request.getHeader("Authorization");
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        String token = authHeader.substring(7);
                        String username = JwtUtil.validateTokenAndGetUsername(token);
                        if (username != null) {
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(username, null, null);
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                    chain.doFilter(request, response);
                }, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
