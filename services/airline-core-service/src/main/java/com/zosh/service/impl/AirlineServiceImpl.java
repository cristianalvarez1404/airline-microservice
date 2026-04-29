package com.zosh.service.impl;

import com.zosh.enums.AirlineStatus;
import com.zosh.mapper.AirlineMapper;
import com.zosh.model.Airline;
import com.zosh.payload.request.AirlineRequest;
import com.zosh.payload.response.AirlineDropdownItem;
import com.zosh.payload.response.AirlineResponse;
import com.zosh.repository.AirlineRepository;
import com.zosh.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public AirlineResponse createAirline(AirlineRequest request, Long ownerId) {
        Airline airline = AirlineMapper.toEntity(request,ownerId);
        Airline savedAirline = airlineRepository.save(airline);

        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public AirlineResponse getAirlineByOwner(Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new Exception("airline not found"));

        return AirlineMapper.toResponse(airline);
    }

    @Override
    public AirlineResponse getAirlineById(Long id) throws Exception {
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new Exception("airline not found"));

        return AirlineMapper.toResponse(airline);
    }

    @Override
    public Page<AirlineResponse> getAllAirlines(Pageable pageable) {
        return airlineRepository.findAll(pageable).map(
                AirlineMapper::toResponse
        );
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest request, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new Exception("airline not found"));

        AirlineMapper.updateEntity(airline,request);
        Airline savedAirline = airlineRepository.save(airline);

        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public void deleteAirline(Long id, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new Exception("airline not found"));

        airlineRepository.delete(airline);
    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) throws Exception {
        Airline airline = airlineRepository.findById(airlineId)
                .orElseThrow(() -> new Exception("airline not found"));

        airline.setStatus(status);
        Airline updatedAirline = airlineRepository.save(airline);

        return AirlineMapper.toResponse(updatedAirline);
    }

    @Override
    public List<AirlineDropdownItem> getAirlineDropdown()
    {
        return airlineRepository.findByStatus(AirlineStatus.ACTIVE)
                .stream()
                .map(a -> AirlineDropdownItem.builder()
                        .id(a.getId())
                        .name(a.getName())
                        .iataCode(a.getIataCode())
                        .icaoCode(a.getIcaoCode())
                        .logoUrl(a.getLogoUrl())
                        .build()).toList();

    }
}
