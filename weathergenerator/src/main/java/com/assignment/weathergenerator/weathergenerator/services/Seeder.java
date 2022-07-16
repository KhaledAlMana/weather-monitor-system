package com.assignment.weathergenerator.weathergenerator.services;

import java.sql.Time;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.assignment.weathergenerator.weathergenerator.data.seed.DummyData;
import com.assignment.weathergenerator.weathergenerator.dto.CreateWeatherReqDTO;
import com.assignment.weathergenerator.weathergenerator.dto.LocationDTO;
import com.assignment.weathergenerator.weathergenerator.dto.WeatherDTO;

@Component
public class Seeder implements ISeeder {
    HashMap<LocationDTO, WeatherDTO> weatherData;
    Logger logger = LoggerFactory.getLogger(Seeder.class);

    @Value("${SEEDER_ENDPOINT}")
    private String SEEDER_ENDPOINT;

    @Value("${BASIC_AUTH_CREDS}")
    private String BASIC_AUTH_CREDS;

    @Scheduled(cron = "${SEEDER_CRON_EXPRESSION}")
    @Override
    public void seed() throws Exception {
        weatherData = generateData();
        weatherData.forEach((location, weather) -> {
            try {
                CreateWeatherReqDTO createWeatherReqDTO = CreateWeatherReqDTO.builder()
                        .weatherId(UUID.randomUUID())
                        .locationName(location.getLocationName())
                        .latitude(location.getLatitude())
                        .longitude(location.getLongitude())
                        .time(weather.getTime())
                        .celsiusDegree(weather.getCelsiusDegree())
                        .build();
                seedRequest(createWeatherReqDTO);
            } catch (Exception e) {
                logger.error("Error while seeding data", e);
            }
        });
    }

    @Override
    public void seedRequest(CreateWeatherReqDTO createWeatherReqDTO) {
        RestTemplate restTemplate = new RestTemplate();
        // Typesafety issue here...
        HttpEntity<String> httpEntity = new HttpEntity(createWeatherReqDTO, getHeaders());
        ResponseEntity<Object> response = restTemplate.postForEntity(SEEDER_ENDPOINT, httpEntity,
                Object.class);
        if (response.getStatusCode() == HttpStatus.ACCEPTED) {
            logger.info("Seeded weather data successfully: {}",
                    createWeatherReqDTO.toString());
        } else {
            logger.error("Error while seeding data: {}", createWeatherReqDTO.toString());
            logger.error("ENDPOINT: {}", SEEDER_ENDPOINT);
            logger.error("Response: {}", response.toString());
        }
    }

    @Override
    public HashMap<LocationDTO, WeatherDTO> generateData() {
        List<LocationDTO> locations = DummyData.locations;
        HashMap<LocationDTO, WeatherDTO> weatherData = new HashMap<LocationDTO, WeatherDTO>();
        for (LocationDTO location : locations) {
            Random random = new Random();
            Date date = new Date(System.currentTimeMillis());
            int celsiusDegree = random.nextInt(50) - 10; // -10 to 50 celsius degree
            Time time = new Time(date.getTime());
            WeatherDTO weatherDTO = WeatherDTO.builder()
                    .celsiusDegree(celsiusDegree)
                    .time(time)
                    .build();
            weatherData.put(location, weatherDTO);
        }

        return weatherData;
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(Base64.getEncoder().encodeToString(BASIC_AUTH_CREDS.getBytes()));
        return headers;
    }
}
