package com.assignment.mintorapp.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import java.sql.Time;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Builder
@Jacksonized
public class WeatherDTO {

    @NotNull
    @JsonProperty
    private UUID weatherId;

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
    private Time time; // 23:55:00

    @NotNull
    @JsonProperty
    private int celsiusDegree;
}
