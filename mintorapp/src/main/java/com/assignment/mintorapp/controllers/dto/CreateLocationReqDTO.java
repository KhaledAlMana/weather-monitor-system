package com.assignment.mintorapp.controllers.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateLocationReqDTO {
    @NotNull
    private String locationName;
    @NotNull
    private double latitude;
    @NotNull
    private double longitude;
}
