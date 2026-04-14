package com.zosh.repository;

import com.zosh.model.Airport;
import com.zosh.payload.response.AirportResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport,Long> {
    Airport findByIataCode(String iataCode);
    List<AirportResponse> findByCityId(Long cityId);
}
