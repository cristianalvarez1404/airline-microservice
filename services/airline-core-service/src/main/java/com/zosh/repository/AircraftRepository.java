package com.zosh.repository;

import com.zosh.model.Aircraft;
import com.zosh.payload.response.AircraftResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AircraftRepository extends JpaRepository<Aircraft,Long> {
    List<Aircraft> findByAirlineId(Long airlineId);
    Boolean existByCode(String code);
    Aircraft findByIdAndAirlineId(Long id, Long airlineId);
}
