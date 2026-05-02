package com.zosh.service.impl;

import com.zosh.enums.FlightStatus;
import com.zosh.mapper.FlightMapper;
import com.zosh.model.Flight;
import com.zosh.payload.request.FlightRequest;
import com.zosh.payload.response.AircraftResponse;
import com.zosh.payload.response.AirlineResponse;
import com.zosh.payload.response.AirportResponse;
import com.zosh.payload.response.FlightResponse;
import com.zosh.repository.FlightRepository;
import com.zosh.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    @Override
    public FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) throws Exception {
        if(flightRepository.existsByFlightNumber(flightRequest.getFlightNumber())){
            throw new Exception("Flight already exists!");
        }

        Flight flight = FlightMapper.toEntity(flightRequest);
        flight.setAirlineId(airlineId);
        Flight saved = flightRepository.save(flight);

        return convertToFlightResponse(saved);
    }

    @Override
    public Page<FlightResponse> getFlightByAirline(Long airlineId, Long departureAirportId, Long arrivalAirportId, Pageable pageable) {

        return flightRepository.findByAirlineId(airlineId,
                departureAirportId,
                arrivalAirportId,pageable)
                .map(this::convertToFlightResponse);
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

    public FlightResponse convertToFlightResponse(Flight flight){
        AircraftResponse aircraft = AircraftResponse.builder()
                .id(flight.getAircraftId())
                .build();

        AirlineResponse airline = AirlineResponse.builder()
                .id(flight.getAirlineId())
                .build();

        AirportResponse departureAirport = AirportResponse.builder()
                .id(flight.getDepartureAirportId())
                .build();

        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(flight.getArrivalAirportId())
                .build();

        return FlightMapper.toResponse(flight, aircraft, airline, departureAirport, arrivalAirport);
    }
}
