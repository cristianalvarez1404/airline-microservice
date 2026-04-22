package com.zosh.mapper;

import com.zosh.embeddable.Support;
import com.zosh.enums.AirlineStatus;
import com.zosh.model.Airline;
import com.zosh.payload.request.AirlineRequest;
import com.zosh.payload.response.AirlineResponse;

public class AirlineMapper {

    public static Airline toEntity(AirlineRequest request, Long ownerId) {
        if(request == null) return null;

        Airline airline = Airline.builder()
                .iataCode(request.getIataCode())
                .icaoCode(request.getIcaoCode())
                .ownerId(ownerId)
                .name(request.getName())
                .alias(request.getAlias())
                .logoUrl(request.getLogoUrl())
                .website(request.getWebsite())
                .status(request.getStatus())
                .alliance(request.getAlliance())
                .headquartersCityId(request.getHeadquartersCityId())
                // .updatedById(LocalDateTime.now())
                // .createdAt(LocalDateTime.now())
                // .updatedAt(LocalDateTime.now())
                .build();

        if(request.getSupportEmail() != null
        || request.getSupportPhone() != null
        || request.getSupportHours() != null
        ){
            airline.setSupport(
                    Support.builder()
                            .email(request.getSupportEmail())
                            .hours(request.getSupportHours())
                            .phone(request.getSupportPhone())
                            .build()
            );
        }

        return airline;
    }

    public static AirlineResponse toResponse(Airline airline){
        if(airline == null) return null;

        return AirlineResponse.builder()
                .iataCode(airline.getIataCode())
                .icaoCode(airline.getIcaoCode())
                .ownerId(airline.getOwnerId())
                .name(airline.getName())
                .alias(airline.getAlias())
                .logoUrl(airline.getLogoUrl())
                .website(airline.getWebsite())
                .status(airline.getStatus())
                .alliance(airline.getAlliance())
                .support(airline.getSupport())
                 .updatedById(airline.getUpdatedById())
                 .createAt(airline.getCreatedAt())
                 .updatedAt(airline.getUpdatedAt())
                .build();
    }

    public static void updateEntity(Airline airline, AirlineRequest request){
        if(airline == null || request == null) return;

        airline.setIataCode(request.getIataCode());
        airline.setIcaoCode(request.getIcaoCode());
        airline.setName(request.getName());
        airline.setAlias(request.getAlias());
        airline.setLogoUrl(request.getLogoUrl());
        airline.setWebsite(request.getWebsite());
        airline.setStatus(request.getStatus());
        airline.setAlliance(request.getAlliance());
        airline.setHeadquartersCityId(request.getHeadquartersCityId());

        if(airline.getSupport() == null){
            airline.setSupport(new Support());
        }

        airline.getSupport().setEmail(request.getSupportEmail());
        airline.getSupport().setPhone(request.getSupportPhone());
        airline.getSupport().setHours(request.getSupportHours());
    }
}