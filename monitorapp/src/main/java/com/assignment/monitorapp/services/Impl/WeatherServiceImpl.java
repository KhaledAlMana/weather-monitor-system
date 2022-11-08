package com.assignment.monitorapp.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.assignment.monitorapp.controllers.dto.CreateLocationReqDTO;
import com.assignment.mq.dto.CreateWeatherReqDTO;
import com.assignment.monitorapp.data.entities.Location;
import com.assignment.monitorapp.data.entities.Weather;
import com.assignment.monitorapp.data.projections.AverageWeatherView;
import com.assignment.monitorapp.data.repositories.LocationRepository;
import com.assignment.monitorapp.data.repositories.WeatherRepository;
import com.assignment.monitorapp.services.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final LocationRepository locationRepository;

    public WeatherServiceImpl(WeatherRepository weatherRepository, LocationRepository locationRepository) {
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
    }

    public void weatherStreamHandler(CreateWeatherReqDTO message) {
        System.out.println("Consumed streamed message: " + message);
        Location location = this.upsertLocation(CreateLocationReqDTO.builder()
                .locationName(message.getLocationName())
                .latitude(message.getLatitude())
                .longitude(message.getLongitude()).build());

        Weather weather = new Weather();
        weather.setExternalId(message.getWeatherId());
        weather.setLocation(location);
        weather.setTime(message.getTime());
        weather.setCelsiusDegree(message.getCelsiusDegree());
        weather.setReceivedAt(message.getReceivedAt());
        this.weatherRepository.save(weather);
    }

    @Override
    public Location upsertLocation(CreateLocationReqDTO createLocationReqDTO) {
        Optional<Location> location = this.locationRepository.findByLatAndLng(createLocationReqDTO.getLatitude(),
                createLocationReqDTO.getLongitude());
        if (location.isPresent()) {
            return location.get();
        }
        Location newLocation = new Location();
        newLocation.setName(createLocationReqDTO.getLocationName());
        newLocation.setLat(createLocationReqDTO.getLatitude());
        newLocation.setLng(createLocationReqDTO.getLongitude());
        return this.locationRepository.save(newLocation);
    }

    @Override
    public List<Weather> getLatestWeatherForAll() {
        return this.weatherRepository.findLatestWeatherForAll();
    }

    @Override
    public Optional<AverageWeatherView> getAverageWeatherForOne(UUID locationId) {
        return this.weatherRepository.findAverageWeatherByLocation(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return this.locationRepository.findAll();
    }

}
