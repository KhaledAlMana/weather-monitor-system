package com.assignment.monitorapp.services.Impl;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import com.assignment.monitorapp.services.WeatherStream;
import com.assignment.mq.dto.CreateWeatherReqDTO;

@Service
public class WeatherStreamImpl implements WeatherStream {
    @Value("${app.weather_topic}")
    private String TOPIC;

    private WeatherServiceImpl weatherService;

    public WeatherStreamImpl(WeatherServiceImpl weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    @Bean
    public KStream<Long, CreateWeatherReqDTO> stream(StreamsBuilder builder) {
        JsonSerde<CreateWeatherReqDTO> createWeatherReqDTOSerde = new JsonSerde<>(CreateWeatherReqDTO.class);
        KStream<Long, CreateWeatherReqDTO> stream = builder
                .stream(TOPIC, Consumed.with(Serdes.Long(), createWeatherReqDTOSerde))
                .peek((k, o) -> {
                    weatherService.weatherStreamHandler(o);
                });
        return stream;
    }

}
