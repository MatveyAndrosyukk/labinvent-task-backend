package com.example.labinventtaskbackend.controllers;

import com.example.labinventtaskbackend.dto.AuthenticationRequestDto;
import com.example.labinventtaskbackend.dto.AuthenticationResponseDto;
import com.example.labinventtaskbackend.models.User;
import com.example.labinventtaskbackend.services.AuthenticationService;
import com.example.labinventtaskbackend.services.UserService;
import com.example.labinventtaskbackend.utils.RolesToStringConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(
        origins = "*"
)
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping()
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();

        String token = authenticationService.login(requestDto);

        User user = userService.findByUsername(username);
        return ResponseEntity.ok(new AuthenticationResponseDto(username, token, RolesToStringConverter.convert(user.getRoles())));
    }
}
