package com.zosh.repository;

import com.zosh.model.Airport;
import com.zosh.payload.response.AirportResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport,Long> {
    Optional<Airport> findByIataCode(String iataCode);
    List<AirportResponse> findByCityId(Long cityId);
}
