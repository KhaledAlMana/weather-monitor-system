package com.assignment.monitorapp.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "pk_weather_idx", columnList = "id", unique = true),
        @Index(name = "fk_weather_location_idx", columnList = "location_id"),
        @Index(name = "weather_receivedAtx", columnList = "receivedAt")
})
public class Weather {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private UUID id;

    @Column(nullable = false)
    private UUID externalId;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private Time time; // 23:55

    @Column(nullable = false)
    private int celsiusDegree;

    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedAt;
}
