package com.assignment.weathergenerator.weathergenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationDTO {

    private String locationName;
    private double latitude;
    private double longitude;

}