package com.example.SensorWeatherApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "measurment")
public class Measurement {
    @Id
    @JsonIgnore
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "temperature")
    @NotNull(message = "Value of temperature should not be empty")
    @Range(min = -100, max = 100, message = "The temperature should be between -100 and 100 degrees.")
    private Double temperature;

    @Column(name = "raining")
    @NotNull(message = "This field should not be empty")
    private Boolean raining;

    public Boolean isRaining() {
        return raining;
    }

    @Column(name = "measurement_date_time")
    @NotNull
    private LocalDateTime measurementDateTime;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    @NotNull(message = "This field should not be empty")
    Sensor sensor;

}
