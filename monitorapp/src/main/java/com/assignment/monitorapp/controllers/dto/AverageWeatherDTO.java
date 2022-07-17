package com.assignment.monitorapp.controllers.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class AverageWeatherDTO {
    @NotNull
    @JsonProperty
    private UUID locationId;

    @NotNull
    @JsonProperty
    private String locationName;

    @NotNull
    @JsonProperty
    private double latitude;

    @NotNull
    @JsonProperty
    private double longitude;

    @NotNull
    @JsonProperty
    private float averageCelsiusDegree;

}
