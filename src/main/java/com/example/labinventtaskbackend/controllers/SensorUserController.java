package com.example.labinventtaskbackend.controllers;

import com.example.labinventtaskbackend.models.Sensor;
import com.example.labinventtaskbackend.services.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@CrossOrigin(origins = "*")
public class SensorUserController {
    private final SensorService sensorService;

    public SensorUserController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping()
    public ResponseEntity<List<Sensor>> getAllSensors(){
        List<Sensor> sensors = sensorService.findAll();

        if (sensors == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(sensors);
    }

    @GetMapping("{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable Long id){
        Sensor sensor = sensorService.findById(id);

        if (sensor == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(sensor);
    }
}
