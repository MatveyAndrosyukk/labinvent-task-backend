package com.example.labinventtaskbackend.services.impls;

import com.example.labinventtaskbackend.models.Sensor;
import com.example.labinventtaskbackend.exception.OperationFailedException;
import com.example.labinventtaskbackend.repositories.SensorRepository;
import com.example.labinventtaskbackend.services.SensorService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SensorServiceImpl implements SensorService {
    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @SneakyThrows
    @Override
    public List<Sensor> findAll() {
        List<Sensor> sensors;
        try {
            sensors = sensorRepository.findAll();
        }catch (Exception exception){
            throw new OperationFailedException("findAll failed");
        }
        log.info("IN findAll sensors: {} were successfully received", sensors);
        return sensors;
    }

    @SneakyThrows
    @Override
    public Sensor findById(Long id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new OperationFailedException("findById failed"));
        log.info("IN findById sensor: {} was successfully received", sensor);
        return sensor;
    }

    @SneakyThrows
    @Override
    public Sensor save(Sensor sensor) {
        Sensor savedSensor;
        try {
            savedSensor = sensorRepository.save(sensor);
        }catch (Exception exception){
            throw new OperationFailedException("save failed");
        }
        log.info("IN save sensor: {} was successfully saved", savedSensor);
        return savedSensor;
    }

    @SneakyThrows
    @Override
    public Long deleteById(Long id) {
        try {
            sensorRepository.deleteById(id);
        }catch (Exception exception){
            throw new OperationFailedException("deleteById failed");
        }
        log.info("IN deleteById sensor with id: {} was successfully deleted", id);
        return id;
    }
}
