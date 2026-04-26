package com.zosh.mapper;

import com.zosh.model.Aircraft;
import com.zosh.model.Airline;
import com.zosh.payload.request.AircraftRequest;
import com.zosh.payload.response.AircraftResponse;

public class AircraftMapper {

    public static Aircraft toEntity(AircraftRequest request, Airline airline){
        if(request == null) return null;

        return Aircraft.builder()
                .code(request.getCode())
                .model(request.getModel())
                .manufacturer(request.getManufacturer())
                .seatingCapacity(request.getSeatingCapacity())
                .economySeats(request.getEconomySeats())
                .premiumEconomySeats(request.getPremiumEconomySeats())
                .businessSeats(request.getBusinessSeats())
                .firstClassSeats(request.getFirstClassSeats())
                .rangeKm(request.getRangeKm())
                .cruisingSpeedKmh(request.getCruisingSpeedKmh())
                .maxAltitudeFt(request.getMaxAltitudeFt())
                .yearOfManufacture(request.getYearOfManufacture())
                .registrationDate(request.getRegistrationDate())
                .nextMaintenanceDate(request.getNextMaintenanceDate())
                .status(request.getStatus())
                .isAvailable(request.getIsAvailable())
                .airline(airline)
                .currentAirportId(request.getCurrentAirportId())
                .build();
    }

    public static AircraftResponse toResponse(Aircraft aircraft){
        if(aircraft == null) return null;

        return AircraftResponse.builder()
                .id(aircraft.getId())
                .code(aircraft.getCode())
                .model(aircraft.getModel())
                .manufacturer(aircraft.getManufacturer())
                .seatingCapacity(aircraft.getSeatingCapacity())
                .economySeats(aircraft.getEconomySeats())
                .premiumEconomySeats(aircraft.getPremiumEconomySeats())
                .businessSeats(aircraft.getBusinessSeats())
                .firstClassSeats(aircraft.getFirstClassSeats())
                .rangeKm(aircraft.getRangeKm())
                .cruisingSpeedKmh(aircraft.getCruisingSpeedKmh())
                .maxAltitudeFt(aircraft.getMaxAltitudeFt())
                .yearOfManufacture(aircraft.getYearOfManufacture())
                .registrationDate(aircraft.getRegistrationDate())
                .nextMaintenanceDate(aircraft.getNextMaintenanceDate())
                .status(aircraft.getStatus())
                .isAvailable(aircraft.getIsAvailable())
                .airlineId(aircraft.getAirline() != null ? aircraft.getAirline().getId() : null)
                .airlineName(aircraft.getAirline() != null ? aircraft.getAirline().getName() : null)
                .airlineIataCode(aircraft.getAirline() != null ? aircraft.getAirline().getIataCode() : null)
                .currentAirportId(aircraft.getCurrentAirportId())
                .totalSeats(aircraft.getTotalSeats())
                .requiresMaintenance(aircraft.requiresMaintenance())
                .isOperational(aircraft.isOperational())
                .createdAt(aircraft.getCreatedAt())
                .updatedAt(aircraft.getUpdatedAt())
                .build();
    }

    public static void updateEntity(Aircraft aircraft, AircraftRequest request){
        if(aircraft == null || request == null) return;

        aircraft.setId(aircraft.getId());
        aircraft.setCode(request.getCode());
        aircraft.setModel(aircraft.getModel());
        aircraft.setManufacturer(aircraft.getManufacturer());
        aircraft.setSeatingCapacity(aircraft.getSeatingCapacity());
        aircraft.setEconomySeats(aircraft.getEconomySeats());
        aircraft.setPremiumEconomySeats(aircraft.getPremiumEconomySeats());
        aircraft.setBusinessSeats(aircraft.getBusinessSeats());
        aircraft.setFirstClassSeats(aircraft.getFirstClassSeats());
        aircraft.setRangeKm(aircraft.getRangeKm());
        aircraft.setCruisingSpeedKmh(aircraft.getCruisingSpeedKmh());
        aircraft.setMaxAltitudeFt(aircraft.getMaxAltitudeFt());
        aircraft.setYearOfManufacture(aircraft.getYearOfManufacture());
        aircraft.setRegistrationDate(aircraft.getRegistrationDate());
        aircraft.setNextMaintenanceDate(aircraft.getNextMaintenanceDate());
        aircraft.setStatus(aircraft.getStatus());
        aircraft.setIsAvailable(aircraft.getIsAvailable());
        aircraft.setCurrentAirportId(aircraft.getCurrentAirportId());
    }
}
