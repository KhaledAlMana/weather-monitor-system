package com.assignment.monitorapp.data.repositories;

import com.assignment.monitorapp.data.entities.Weather;
import com.assignment.monitorapp.data.projections.AverageWeatherView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, UUID> {

        // @Query(value = "SELECT w.location_id, w.location_id, l.name, l.lat, l.lng, "
        // +
        // "AVG(w.celsius_degree) " +
        // "FROM weather w , location l " +
        // "WHERE w.location_id=l.id AND w.location_id = :locationId " +
        // "GROUP BY l.id, w.location_id, l.name, l.lat, l.lng;", nativeQuery = true)
        @Query(value = "SELECT locationId, locationName, latitude, longitude, avgCelsiusDegree FROM (" +
                        "SELECT CAST(w.location_id as varchar) as locationId, " +
                        " l.name as locationName, " +
                        "l.lat as latitude, " +
                        " l.lng as longitude, " +
                        "AVG(w.celsius_degree) OVER(PARTITION BY w.location_id) as avgCelsiusDegree, "
                        +
                        "ROW_NUMBER() OVER (ORDER BY w.location_id desc) as row_number " +
                        "FROM weather w, location l " +
                        "WHERE w.location_id = l.id AND w.location_id = :locationId " +
                        ") as avg WHERE avg.row_number = 1", nativeQuery = true)
        Optional<AverageWeatherView> findAverageWeatherByLocation(@Param("locationId") UUID locationId);

        @Query(value = "SELECT id, external_id, location_id, " +
                        "name, lat, lng, time, celsius_degree, received_at " +
                        "FROM (SELECT " +
                        "CAST(w.id as VARCHAR), " +
                        "w.external_id, " +
                        "w.location_id, " +
                        "l.name, " +
                        "l.lat, l.lng, " +
                        "w.time, " +
                        "w.celsius_degree, " +
                        "w.received_at, " +
                        "ROW_NUMBER() OVER(" +
                        "PARTITION BY w.location_id ORDER BY w.received_at DESC " +
                        ") as row_number " +
                        "FROM weather w, location l " +
                        "WHERE w.location_id = l.id " +
                        ") as latest WHERE latest.row_number = 1", nativeQuery = true)

        List<Weather> findLatestWeatherForAll();
}
