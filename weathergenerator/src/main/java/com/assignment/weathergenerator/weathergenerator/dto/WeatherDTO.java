package com.assignment.weathergenerator.weathergenerator.dto;

import java.sql.Time;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WeatherDTO {
    private int celsiusDegree;
    private Time time;
}