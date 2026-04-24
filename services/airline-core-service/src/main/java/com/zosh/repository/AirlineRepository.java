package com.zosh.repository;


import java.util.List;
import java.util.Optional;

import com.zosh.enums.AirlineStatus;
import com.zosh.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AirlineRepository extends JpaRepository<Airline,Long> {
    Optional<Airline> findByOwnerId(Long ownerId);
    List<Airline> findByStatus(AirlineStatus status);
}
