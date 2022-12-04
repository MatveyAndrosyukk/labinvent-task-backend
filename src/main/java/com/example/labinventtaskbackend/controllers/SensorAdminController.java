package com.example.labinventtaskbackend.controllers;

import com.example.labinventtaskbackend.dto.SensorDto;
import com.example.labinventtaskbackend.models.Sensor;
import com.example.labinventtaskbackend.services.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/sensors")
@CrossOrigin(
        origins = "*",
        allowedHeaders = {"Authorization"}
)
@RequiredArgsConstructor
public class SensorAdminController {
    private final SensorService sensorService;

    @PostMapping()
    public ResponseEntity<Sensor> saveSensor(@Validated @RequestBody SensorDto sensorDto){
        Sensor sensor = SensorDto.buildSensor(sensorDto);
        return ResponseEntity.ok(sensorService.save(sensor));
    }

    @PutMapping()
    public ResponseEntity<Sensor> updateSensor(@Validated @RequestBody SensorDto sensorDto){
        Sensor sensor = SensorDto.buildSensor(sensorDto);
        return ResponseEntity.ok(sensorService.save(sensor));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteSensor(@PathVariable Long id){
        return ResponseEntity.ok(sensorService.deleteById(id));
    }
}
