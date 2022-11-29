package com.example.labinventtaskbackend.config;

import com.example.labinventtaskbackend.models.Role;
import com.example.labinventtaskbackend.models.User;
import com.example.labinventtaskbackend.security.jwt.JwtConfigurer;
import com.example.labinventtaskbackend.security.jwt.JwtTokenProvider;
import com.example.labinventtaskbackend.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfig{
    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

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
                                        .requestMatchers("/auth/**").permitAll()
                                        .requestMatchers("/admin/sensors/**").hasAuthority("ROLE_ADMIN")
                                        .requestMatchers("/sensors/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
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

    @Bean
    public AuthenticationManager authenticationManagerBean(){
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String username = authentication.getPrincipal() + "";
                String password = authentication.getCredentials() + "";

                User user = userService.findByUsername(username);
                if (user == null) {
                    throw new BadCredentialsException("1000");
                }
                if (!passwordEncoder.matches(password, user.getPassword())) {
                    throw new BadCredentialsException("1000");
                }
                List<Role> roles = user.getRoles();
                return new UsernamePasswordAuthenticationToken(username, null, mapRolesToGrantedAuthorities(roles));
            }
        };
    }

    private List<GrantedAuthority> mapRolesToGrantedAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
