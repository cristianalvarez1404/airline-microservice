package com.zosh.service.impl;

import com.zosh.enums.FlightStatus;
import com.zosh.payload.request.FlightRequest;
import com.zosh.payload.response.FlightResponse;
import com.zosh.repository.FlightRepository;
import com.zosh.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    @Override
    public FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) {
        return null;
    }

    @Override
    public Page<FlightResponse> getFlightByAirline(Long airlineId, Long departureAirportId, Long arrivalAirportId, Pageable pageable) {
        return null;
    }

    @Override
    public FlightResponse getFlightById(Long id) {
        return null;
    }

    @Override
    public FlightResponse updateFlight(Long id, FlightRequest flightRequest) {
        return null;
    }

    @Override
    public FlightResponse changeStatus(Long id, FlightStatus status) {
        return null;
    }

    @Override
    public void deleteFlight(Long id) {

    }
}
