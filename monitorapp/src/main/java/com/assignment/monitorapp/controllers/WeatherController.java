package com.assignment.monitorapp.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.monitorapp.controllers.dto.AverageWeatherDTO;
import com.assignment.monitorapp.controllers.dto.LocationDTO;
import com.assignment.monitorapp.controllers.dto.WeatherDTO;
import com.assignment.monitorapp.services.Impl.WeatherServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class WeatherController {
    private WeatherServiceImpl weatherService;

    public WeatherController(WeatherServiceImpl weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String index() {
        return "Welcome Tomato";
    }

    @GetMapping("/weather/latest")
    public List<WeatherDTO> getAll() {
        return this.weatherService.getLatestWeatherForAll().stream()
                .map(mapper -> WeatherDTO.builder().weatherId(mapper.getId())
                        .locationName(mapper.getLocation().getName()).latitude(mapper.getLocation().getLat())
                        .longitude(mapper.getLocation().getLng()).time(mapper.getTime())
                        .celsiusDegree(mapper.getCelsiusDegree()).build())
                .collect(Collectors.toList());
    }

    @GetMapping("/weather/average/{locationId}")
    public ResponseEntity<AverageWeatherDTO> getAverage(@PathVariable UUID locationId) {
        Optional<AverageWeatherDTO> average = this.weatherService.getAverageWeatherForOne(locationId)
                .map(mapper -> AverageWeatherDTO.builder()
                        .locationId(mapper.getLocationId())
                        .locationName(mapper.getLocationName())
                        .latitude(mapper.getLatitude()).longitude(mapper.getLongitude())
                        .averageCelsiusDegree(mapper.getAvgCelsiusDegree()).build());

        return average.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/locations")
    public List<LocationDTO> getAllLocations() {
        return this.weatherService.getAllLocations().stream()
                .map(mapper -> LocationDTO.builder()
                        .locationId(mapper.getId())
                        .locationName(mapper.getName())
                        .latitude(mapper.getLat()).longitude(mapper.getLng()).build())
                .collect(Collectors.toList());

    }
}
