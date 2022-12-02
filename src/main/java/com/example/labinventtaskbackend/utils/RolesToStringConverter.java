package com.example.labinventtaskbackend.utils;

import com.example.labinventtaskbackend.models.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RolesToStringConverter {
    public static List<String> convert(List<Role> roles){
        return roles.stream().map(Role::getName).collect(Collectors.toList());
    }
}
