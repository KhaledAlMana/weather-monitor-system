package com.assignment.weathergenerator.weathergenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeathergeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeathergeneratorApplication.class, args);
	}

}
