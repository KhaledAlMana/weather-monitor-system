package com.assignment.weathergenerator.weathergenerator.dto;

import java.sql.Time;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherDTO {
    private int celsiusDegree;
    private Time time;
}