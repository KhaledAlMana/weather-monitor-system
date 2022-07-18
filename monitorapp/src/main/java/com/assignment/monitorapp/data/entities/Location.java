package com.assignment.monitorapp.data.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.geo.Point;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Location {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private UUID id;

    // @OneToMany(mappedBy = "location", cascade = { CascadeType.DETACH })
    // private Set<Weather> weather;

    @Column(nullable = false)
    private String name;

    // private Point location;

    @Column(nullable = false)
    private double lng;

    @Column(nullable = false)
    private double lat;

    // TODO: Add a field for the latest weather data; READ vs WRITE ?

    public Location() {
    }

    public Location(String name, double lng, double lat) {
        this.name = name;
        this.lng = lng;
        this.lat = lat;
    }

}
