package com.example.labinventtaskbackend.services.impls;

import com.example.labinventtaskbackend.dto.AuthenticationRequestDto;
import com.example.labinventtaskbackend.models.User;
import com.example.labinventtaskbackend.security.jwt.JwtTokenProvider;
import com.example.labinventtaskbackend.services.AuthenticationService;
import com.example.labinventtaskbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Override
    @Transactional
    public String login(AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            String password = requestDto.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            return jwtTokenProvider.createToken(username, user.getRoles());
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
