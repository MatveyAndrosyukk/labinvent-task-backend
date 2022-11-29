package com.example.labinventtaskbackend.services;

import com.example.labinventtaskbackend.dto.AuthenticationRequestDto;

public interface AuthenticationService {
    String login(AuthenticationRequestDto requestDto);
}
