package com.assignment.monitorapp.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.assignment.monitorapp.controllers.dto.CreateLocationReqDTO;
import com.assignment.mq.dto.CreateWeatherReqDTO;
import com.assignment.monitorapp.data.entities.Location;
import com.assignment.monitorapp.data.entities.Weather;
import com.assignment.monitorapp.data.projections.AverageWeatherView;

@Service
public interface WeatherService {

    public void consumeWeatherData(CreateWeatherReqDTO message);

    public Location upsertLocation(CreateLocationReqDTO createLocationReqDTO);

    public List<Weather> getLatestWeatherForAll();

    public Optional<AverageWeatherView> getAverageWeatherForOne(UUID locationId);

    public List<Location> getAllLocations();
}
