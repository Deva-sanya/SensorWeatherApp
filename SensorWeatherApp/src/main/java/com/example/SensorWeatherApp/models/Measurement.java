package com.example.SensorWeatherApp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity
@Table(name = "measurment")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temperature")
    @NotEmpty(message = "Value of temperature should not be empty")
    @Size(min = -100, max = 100, message = "The temperature should be between -100 and 100 degrees.")
    double temperature;

    @Column(name = "raining")
    @NotEmpty(message = "This field should not be empty")
    boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotEmpty(message = "This field should not be empty")
    Sensor sensor;


}
