package com.example.labinventtaskbackend.security.jwt;

import com.example.labinventtaskbackend.models.Role;
import com.example.labinventtaskbackend.models.Status;
import com.example.labinventtaskbackend.models.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {
    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getStatus().equals(Status.ACTIVE),
                user.getPassword(),
                mapRolesToGrantedAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> mapRolesToGrantedAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
