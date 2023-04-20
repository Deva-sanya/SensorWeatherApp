package com.example.SensorWeatherApp.controllers;


import com.example.SensorWeatherApp.models.Sensor;
import com.example.SensorWeatherApp.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public String allSensors(Model model) {
        List<Sensor> sensors = sensorService.findAllSensors();
        model.addAttribute("sensors", sensors);
        return "/books/allBooks";
    }

    @GetMapping("/newSensor")
    public String newSensor(@ModelAttribute("sensor") Sensor sensor) {
        return "/books/addBook";
    }

    @PostMapping("/registration")
    public String registrationOfSensor(@ModelAttribute("sensor") @Valid Sensor sensor, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/books/addBook";
        sensorService.saveSensor(sensor);
        return "redirect:/books";
    }

}
