package com.assignment.monitorapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@EnableKafkaStreams
@SpringBootApplication
public class MonitorappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorappApplication.class, args);
	}

}
