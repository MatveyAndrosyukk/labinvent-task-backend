package com.example.labinventtaskbackend.repositories;

import com.example.labinventtaskbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
