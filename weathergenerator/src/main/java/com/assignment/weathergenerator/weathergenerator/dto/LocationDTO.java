package com.assignment.weathergenerator.weathergenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LocationDTO {

    private String locationName;

    private double latitude;

    private double longitude;

}