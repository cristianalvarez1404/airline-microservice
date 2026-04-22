package com.zosh.repository;



import com.zosh.model.Airline;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AirlineRepository extends JpaRepository<Airline,Long> {
    Airline findByOwnerId(Long ownerId);
}
