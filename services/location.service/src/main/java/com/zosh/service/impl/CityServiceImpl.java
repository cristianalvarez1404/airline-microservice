package com.zosh.service.impl;

import com.zosh.mapper.CityMapper;
import com.zosh.model.City;
import com.zosh.payload.request.CityRequest;
import com.zosh.payload.response.CityResponse;
import com.zosh.repository.CityRepository;
import com.zosh.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    @Override
    public CityResponse createCity(CityRequest request) throws Exception {
        if(cityRepository.existsByCityCode(request.getCityCode()))
            throw new Exception("city with given code already exist");

        City city = CityMapper.toEntity(request);
        City result =  cityRepository.save(city);
        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse getCityById(Long id) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new Exception("city not exist with give id")
        );

        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest request) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(() -> new Exception("city not exist with give id"));
        City updatedCity = CityMapper.updateEntity(city, request);
        City response = cityRepository.save(updatedCity);

        return CityMapper.toResponse(response);
    }

    @Override
    public void deleteCity(Long id) {

    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return null;
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return null;
    }

    @Override
    public boolean cityExists(String cityCode) {
        return false;
    }

    @Override
    public boolean validateCityCode(String cityCode) {
        return false;
    }
}
