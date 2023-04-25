package com.example.SensorWeatherApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "measurment")
public class Measurement {
    @Id
    @JsonIgnore
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temperature")
    @NotNull(message = "Value of temperature should not be empty")
    @Range(min = -100, max = 100, message = "The temperature should be between -100 and 100 degrees.")
    double temperature;

    @Column(name = "raining")
    @NotNull(message = "This field should not be empty")
    boolean raining;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotEmpty(message = "This field should not be empty")
    Sensor sensor;

    public Measurement() {

    }

    public Measurement(double randomTemperature, boolean randomRaining) {
    }
}
