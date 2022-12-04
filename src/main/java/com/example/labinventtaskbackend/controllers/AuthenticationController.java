package com.example.labinventtaskbackend.controllers;

import com.example.labinventtaskbackend.dto.AuthenticationRequestDto;
import com.example.labinventtaskbackend.dto.AuthenticationResponseDto;
import com.example.labinventtaskbackend.models.User;
import com.example.labinventtaskbackend.services.AuthenticationService;
import com.example.labinventtaskbackend.services.UserService;
import com.example.labinventtaskbackend.utils.RolesToStringConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = "http://localhost:4200"
)
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping()
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();

        String token = authenticationService.login(requestDto);

        User user = userService.findByUsername(username);
        return ResponseEntity.ok(new AuthenticationResponseDto(username, token, RolesToStringConverter.convert(user.getRoles())));
    }
}
