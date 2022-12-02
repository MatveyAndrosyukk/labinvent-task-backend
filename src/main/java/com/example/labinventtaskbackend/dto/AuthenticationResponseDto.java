package com.example.labinventtaskbackend.dto;

import com.example.labinventtaskbackend.models.Role;
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
