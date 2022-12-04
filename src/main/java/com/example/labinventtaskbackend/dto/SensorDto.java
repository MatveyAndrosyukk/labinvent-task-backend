package com.example.labinventtaskbackend.dto;

import com.example.labinventtaskbackend.models.Sensor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDto {
    @NotNull
    private Long id;

    @NotEmpty
    @Length(max = 30)
    private String name;

    @NotEmpty
    @Length(max = 15)
    private String model;

    @NotNull
    private Integer rangeFrom;

    @NotNull
    private Integer rangeTo;

    @NotEmpty
    private String type;

    @NotEmpty
    private String unit;

    @NotEmpty
    @Length(max = 40)
    private String location;

    @NotEmpty
    @Length(max = 200)
    private String description;

    public static Sensor buildSensor(SensorDto sensorDto){
        return Sensor.builder()
                        .id(sensorDto.getId())
                        .name(sensorDto.getName())
                        .model(sensorDto.getModel())
                        .rangeFrom(sensorDto.getRangeFrom())
                        .rangeTo(sensorDto.getRangeTo())
                        .type(sensorDto.getType())
                        .unit(sensorDto.getUnit())
                        .location(sensorDto.getLocation())
                        .description(sensorDto.getDescription()).build();
    }

    public static SensorDto buildSensorDto(Sensor sensor){
        return SensorDto.builder()
                .id(sensor.getId())
                .name(sensor.getName())
                .model(sensor.getModel())
                .rangeFrom(sensor.getRangeFrom())
                .rangeTo(sensor.getRangeTo())
                .type(sensor.getType())
                .unit(sensor.getUnit())
                .location(sensor.getLocation())
                .description(sensor.getDescription()).build();
    }

    public static List<SensorDto> buildSensorDtoList(List<Sensor> sensors){
        return sensors.stream().map(SensorDto::buildSensorDto).collect(Collectors.toList());
    }
}
