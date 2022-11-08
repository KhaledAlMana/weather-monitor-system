package com.assignment.weathergenerator.weathergenerator.dto;

import java.sql.Time;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherDTO {
    private int celsiusDegree;
    private Time time;

    public WeatherDTO(int celsiusDegree, Time time) {
        this.celsiusDegree = celsiusDegree;
        this.time = time;
    }

    public WeatherBuilder tobuilder(int celsiusDegree) {
        return new WeatherBuilder(celsiusDegree);
    }

    public static class WeatherBuilder {
        private int celsiusDegree = 0;
        private Time time = new Time(10, 10, 10);

        private WeatherBuilder(int celsiusDegree) {
            this.celsiusDegree = celsiusDegree;
        }

        public WeatherBuilder celsiusDegree(int celsiusDegree) {
            this.celsiusDegree = celsiusDegree;
            return this;
        }

        public WeatherBuilder time(Time time) {
            this.time = time;
            return this;
        }

        public WeatherDTO build(Time time) {
            return new WeatherDTO(celsiusDegree, time);
        }

    }

}
