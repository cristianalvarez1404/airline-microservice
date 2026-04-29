package com.zosh.service.impl;

import com.zosh.mapper.AircraftMapper;
import com.zosh.model.Aircraft;
import com.zosh.model.Airline;
import com.zosh.payload.request.AircraftRequest;
import com.zosh.payload.request.AirlineRequest;
import com.zosh.payload.response.AircraftResponse;
import com.zosh.repository.AircraftRepository;
import com.zosh.repository.AirlineRepository;
import com.zosh.service.AircraftService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;

    @Override
    public AircraftResponse createAircraft(AircraftRequest request, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(
                        () -> new Exception("airline not exist for this ownerId")
                );
        Aircraft aircraft = AircraftMapper.toEntity(request, airline);

        if(aircraftRepository.existByCode(aircraft.getCode())){
            throw new Exception("code already exist with other aircraft");
        }

        if(aircraft.getSeatingCapacity() < aircraft.getTotalSeats()){
            throw new Exception("seating capacity can't exceed to total seat");
        }

        return AircraftMapper.toResponse(aircraftRepository.save(aircraft));
    }

    @Override
    public AircraftResponse getAircraftById(Long id) throws Exception {
        Aircraft aircraft = aircraftRepository.findById(id).orElseThrow(() -> new Exception("Aircraft not exist with id"));
        return AircraftMapper.toResponse(aircraft);
    }

    @Override
    public List<AircraftResponse> listAllAircraftByOwner(Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(() -> new Exception("this owner don't have airline"));
        return aircraftRepository.findByAirlineId(airline.getId())
                .stream()
                .map(AircraftMapper::toResponse).toList();
    }

    @Override
    public AircraftResponse updateAircraft(Long id, AircraftRequest request, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new Exception("this owner don't have airline"));

        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(id, airline.getId());

        if(aircraft == null){
            throw new Exception("Aircraft not exist with id");
        }

        if(request.getCode() != null
                && !aircraft.getCode().equals(request.getCode())
                && aircraftRepository.existByCode(request.getCode())){
            throw new Exception("code already exist with other aircraft");
        }

        AircraftMapper.updateEntity(aircraft, request);

        return AircraftMapper.toResponse(aircraftRepository.save(aircraft));
    }

    @Override
    public void deleteAircraft(Long id, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new Exception("this owner don't have airline"));

        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(id, airline.getId());
        if(aircraft == null){
            throw new Exception("Aircraft not exist with id");
        }
        aircraftRepository.delete(aircraft);

    }
}
