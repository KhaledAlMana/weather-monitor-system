package com.assignment.mintorapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.assignment.mintorapp.controllers.dto.CreateLocationReqDTO;
import com.assignment.mq.dto.CreateWeatherReqDTO;
import com.assignment.mintorapp.data.entities.Location;
import com.assignment.mintorapp.data.entities.Weather;
import com.assignment.mintorapp.data.projections.AverageWeatherView;

@Service
public interface WeatherService {

    public void consumeWeatherData(CreateWeatherReqDTO message);

    public Location upsertLocation(CreateLocationReqDTO createLocationReqDTO);

    public List<Weather> getLatestWeatherForAll();

    public Optional<AverageWeatherView> getAverageWeatherForOne(UUID locationId);

    public List<Location> getAllLocations();
}
