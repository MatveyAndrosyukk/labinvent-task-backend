package com.example.labinventtaskbackend.services;

import com.example.labinventtaskbackend.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByUsername(String username);

    User findById(Long id);

    Long deleteById(Long id);
}
