package com.example.SensorWeatherApp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name of sensor should not be empty")
    @Size(min = 3, max = 30, message = "The name of the sensor should be between 3 and 30 characters long.")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

}
