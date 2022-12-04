package com.example.labinventtaskbackend.config;

import com.example.labinventtaskbackend.models.Role;
import com.example.labinventtaskbackend.models.User;
import com.example.labinventtaskbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("authenticationManagerBean")
@RequiredArgsConstructor
public class AuthenticationManagerImpl implements AuthenticationManager {
    private final UserService userService;

    private final BCryptPasswordEncoder passwordEncoder;

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

    private List<GrantedAuthority> mapRolesToGrantedAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
