package com.example.labinventtaskbackend.services.impls;

import com.example.labinventtaskbackend.exception.OperationFailedException;
import com.example.labinventtaskbackend.models.Sensor;
import com.example.labinventtaskbackend.repositories.SensorRepository;
import com.example.labinventtaskbackend.services.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    private final SensorRepository sensorRepository;

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public List<Sensor> findAll() {
        List<Sensor> sensors = sensorRepository.findAll();
        log.info("IN findAll sensors: {} were successfully received", sensors);
        return sensors;
    }

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public Sensor findById(Long id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new OperationFailedException("findById failed"));
        log.info("IN findById sensor: {} was successfully received", sensor);
        return sensor;
    }

    @SneakyThrows
    @Transactional
    @Override
    public Sensor save(Sensor sensor) {
        Sensor savedSensor = sensorRepository.save(sensor);
        log.info("IN save sensor: {} was successfully saved", savedSensor);
        return savedSensor;
    }

    @SneakyThrows
    @Transactional
    @Override
    public Long deleteById(Long id) {
        sensorRepository.deleteById(id);
        log.info("IN deleteById sensor with id: {} was successfully deleted", id);
        return id;
    }
}
