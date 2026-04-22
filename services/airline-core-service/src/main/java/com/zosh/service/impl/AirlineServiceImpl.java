package com.zosh.service.impl;

import com.zosh.enums.AirlineStatus;
import com.zosh.payload.request.AirlineRequest;
import com.zosh.payload.response.AirlineDropdownItem;
import com.zosh.payload.response.AirlineResponse;
import com.zosh.repository.AirlineRepository;
import com.zosh.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public AirlineResponse createAirline(AirlineRequest request, Long ownerId) {
        return null;
    }

    @Override
    public AirlineResponse getAirlineByOwner(Long ownerId) {
        return null;
    }

    @Override
    public AirlineResponse getAirlineById(Long id) {
        return null;
    }

    @Override
    public Page<AirlineResponse> getAllAirlines(Pageable pageable) {
        return null;
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest request, Long ownerId) {
        return null;
    }

    @Override
    public void deleteAirline(Long id, Long ownerId) {

    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) {
        return null;
    }

    @Override
    public List<AirlineDropdownItem> getAirlineDropdown() {
        return List.of();
    }
}
