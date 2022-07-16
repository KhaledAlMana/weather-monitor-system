package com.assignment.ingestionservice.services;

import com.assignment.mq.dto.CreateWeatherReqDTO;
import java.sql.Date;
import org.springframework.stereotype.Service;

@Service
public class InjestionService {
    private final WeatherProducer weatherProducer;

    public InjestionService(WeatherProducer weatherProducer) {
        this.weatherProducer = weatherProducer;
    }

    public void createWeather(CreateWeatherReqDTO createWeatherReqDTO) {
        Date date = new Date(System.currentTimeMillis());
        createWeatherReqDTO.setReceivedAt(date);
        this.weatherProducer.sendMessage(createWeatherReqDTO);
    }
}
