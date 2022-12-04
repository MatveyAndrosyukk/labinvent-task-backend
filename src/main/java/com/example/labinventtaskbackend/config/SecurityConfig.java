package com.example.labinventtaskbackend.config;

import com.example.labinventtaskbackend.security.jwt.JwtConfigurer;
import com.example.labinventtaskbackend.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig{
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authz ->
                        {
                            try {
                                authz
                                        .requestMatchers("/api/auth/**").permitAll()
                                        .requestMatchers("/api/admin/sensors/**").hasAuthority("ROLE_ADMIN")
                                        .requestMatchers("/api/sensors/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                                        .anyRequest().authenticated()
                                        .and()
                                        .apply(new JwtConfigurer(jwtTokenProvider));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();
    }
}
