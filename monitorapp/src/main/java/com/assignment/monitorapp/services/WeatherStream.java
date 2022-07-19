package com.assignment.monitorapp.services;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Service;

import com.assignment.mq.dto.CreateWeatherReqDTO;

@Service
public interface WeatherStream {
    public KStream<Long, CreateWeatherReqDTO> stream(StreamsBuilder builder);

}
