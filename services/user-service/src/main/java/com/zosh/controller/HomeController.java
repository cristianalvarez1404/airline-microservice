package com.zosh.controller;

import com.zosh.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("")
    public ApiResponse user() {
        ApiResponse apiResponse = new ApiResponse("Welcome to user service of airline system.");
        return apiResponse;
    }
}
