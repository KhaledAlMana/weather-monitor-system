package com.assignment.mintorapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.assignment.mintorapp.controllers.dto.CreateLocationReqDTO;
import com.assignment.mq.dto.CreateWeatherReqDTO;
import com.assignment.mintorapp.data.entities.Location;
import com.assignment.mintorapp.data.entities.Weather;
import com.assignment.mintorapp.data.projections.AverageWeatherView;
import com.assignment.mintorapp.data.repositories.LocationRepository;
import com.assignment.mintorapp.data.repositories.WeatherRepository;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final String TOPIC = "${app.weather_topic}";
    private final String GPROUP_ID = "${spring.kafka.consumer.group-id}";
    private final WeatherRepository weatherRepository;
    private final LocationRepository locationRepository;

    public WeatherServiceImpl(WeatherRepository weatherRepository, LocationRepository locationRepository) {
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
    }

    // TODO: Implement Stream Instead
    @KafkaListener(topics = TOPIC, groupId = GPROUP_ID)
    public void consumeWeatherData(CreateWeatherReqDTO message) {
        System.out.println("Consumed message: " + message);

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
