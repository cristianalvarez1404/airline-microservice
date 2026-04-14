package com.zosh.service.impl;

import com.zosh.payload.request.AirportRequest;
import com.zosh.payload.response.AirportResponse;
import com.zosh.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceIImp implements AirportService {



    @Override
    public AirportResponse createAirport(AirportRequest request) {
        return null;
    }

    @Override
    public AirportResponse getAirportById(Long id) {
        return null;
    }

    @Override
    public List<AirportResponse> getAllAirports() {
        return List.of();
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest request) {
        return null;
    }

    @Override
    public void deleteAirport(Long id) {

    }

    @Override
    public List<AirportResponse> getAirportByCityId(Long cityId) {
        return List.of();
    }
}
