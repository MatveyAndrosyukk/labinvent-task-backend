package com.example.labinventtaskbackend.services;

import com.example.labinventtaskbackend.models.Sensor;

import java.util.List;

public interface SensorService {
    List<Sensor> findAll();
    Sensor findById(Long id);
    Sensor save(Sensor sensor);
    Long deleteById(Long id);
}
