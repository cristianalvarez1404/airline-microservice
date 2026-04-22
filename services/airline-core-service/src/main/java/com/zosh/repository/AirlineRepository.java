package com.zosh.repository;

import com.zosh.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline,Long> {
    Optional<Airline> findByOwnerId(Long ownerId);
}
