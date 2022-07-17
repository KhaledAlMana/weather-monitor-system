package com.assignment.monitorapp.data.projections;

import java.util.UUID;

public interface AverageWeatherView {

    UUID getLocationId();

    String getLocationName();

    double getLatitude();

    double getLongitude();

    float getAvgCelsiusDegree();

}
