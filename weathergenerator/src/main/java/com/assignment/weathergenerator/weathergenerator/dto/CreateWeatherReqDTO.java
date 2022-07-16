package com.assignment.weathergenerator.weathergenerator.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import java.sql.Time;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Builder
@Jacksonized
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateWeatherReqDTO {
    @JsonProperty
    private UUID weatherId;

    @JsonProperty
    private String locationName;

    @JsonProperty
    private double latitude;

    @JsonProperty
    private double longitude;

    @JsonProperty
    private Time time; // 23:55:00

    @JsonProperty
    private int celsiusDegree;
}
