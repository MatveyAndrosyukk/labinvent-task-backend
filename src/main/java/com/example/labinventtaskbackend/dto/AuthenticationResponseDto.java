package com.example.labinventtaskbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthenticationResponseDto {
    private String username;

    private String token;

    private List<String> roles;
}
