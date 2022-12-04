package com.example.labinventtaskbackend.repositories;

import com.example.labinventtaskbackend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
