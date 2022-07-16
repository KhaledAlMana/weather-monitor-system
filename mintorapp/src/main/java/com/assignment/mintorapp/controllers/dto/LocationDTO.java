package com.assignment.mintorapp.controllers.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class LocationDTO {
    private UUID locationId;
    private String locationName;
    private double latitude;
    private double longitude;
}
