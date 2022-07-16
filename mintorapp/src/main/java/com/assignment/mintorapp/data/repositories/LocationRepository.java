package com.assignment.mintorapp.data.repositories;

import com.assignment.mintorapp.data.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {

    Optional<Location> findByLatAndLng(double lat, double lng);

}
