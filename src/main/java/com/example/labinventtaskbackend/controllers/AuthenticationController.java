package com.example.labinventtaskbackend.controllers;

import com.example.labinventtaskbackend.dto.AuthenticationRequestDto;
import com.example.labinventtaskbackend.dto.AuthenticationResponseDto;
import com.example.labinventtaskbackend.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping()
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto requestDto) {
        String token = authenticationService.login(requestDto);
        return ResponseEntity.ok(new AuthenticationResponseDto(requestDto.getUsername(), token));
    }
}
