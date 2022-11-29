package com.example.labinventtaskbackend.repositories;

import com.example.labinventtaskbackend.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
