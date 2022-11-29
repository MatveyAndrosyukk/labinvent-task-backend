package com.example.labinventtaskbackend.controllers;

import com.example.labinventtaskbackend.models.Sensor;
import com.example.labinventtaskbackend.services.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/sensors")
@CrossOrigin("*")
public class SensorAdminController {
    private final SensorService sensorService;

    public SensorAdminController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping()
    public ResponseEntity<Sensor> saveSensor(@RequestBody Sensor sensor){
        return ResponseEntity.ok(sensorService.save(sensor));
    }

    @PutMapping()
    public ResponseEntity<Sensor> updateSensor(@RequestBody Sensor sensor){
        return ResponseEntity.ok(sensorService.save(sensor));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteSensor(@PathVariable Long id){
        return ResponseEntity.ok(sensorService.deleteById(id));
    }
}
