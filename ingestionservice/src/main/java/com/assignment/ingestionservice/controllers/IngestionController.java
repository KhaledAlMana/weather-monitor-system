package com.assignment.ingestionservice.controllers;

import com.assignment.mq.dto.CreateWeatherReqDTO;
import com.assignment.ingestionservice.services.InjestionService;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class IngestionController {
    private final InjestionService injestionService;

    public IngestionController(InjestionService injestionService) {
        this.injestionService = injestionService;
    }

    @GetMapping("/")
    public String helloTomato() {
        return "Hello Tomato";
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PostMapping("/weather")
    public void createWeather(@Validated @RequestBody CreateWeatherReqDTO createWeatherReqDTO) {
        this.injestionService.createWeather(createWeatherReqDTO);
    }
}
