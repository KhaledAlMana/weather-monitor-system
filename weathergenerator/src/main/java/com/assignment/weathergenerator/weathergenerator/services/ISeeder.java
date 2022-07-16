package com.assignment.weathergenerator.weathergenerator.services;

import java.net.URISyntaxException;
import java.util.HashMap;

import com.assignment.weathergenerator.weathergenerator.dto.CreateWeatherReqDTO;
import com.assignment.weathergenerator.weathergenerator.dto.LocationDTO;
import com.assignment.weathergenerator.weathergenerator.dto.WeatherDTO;

public interface ISeeder {

    public void seed() throws Exception;

    // Hardcoded data for testing
    public HashMap<LocationDTO, WeatherDTO> generateData();

    public void seedRequest(CreateWeatherReqDTO createWeatherReqDTO) throws URISyntaxException;

}
