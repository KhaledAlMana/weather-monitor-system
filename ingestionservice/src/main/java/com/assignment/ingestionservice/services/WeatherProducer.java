
package com.assignment.ingestionservice.services;

import com.assignment.mq.dto.CreateWeatherReqDTO;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WeatherProducer {
    @Value("${app.weather_topic}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String, CreateWeatherReqDTO> kafkaTemplate;

    public void sendMessage(CreateWeatherReqDTO data) {
        this.kafkaTemplate.send(TOPIC, data);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }

}