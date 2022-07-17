package com.assignment.mq.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Builder
@Jacksonized
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateWeatherReqDTO {
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

    @JsonIgnoreProperties(ignoreUnknown = true)
    Date receivedAt;

}
