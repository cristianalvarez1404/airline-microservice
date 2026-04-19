package com.zosh.service;

import com.zosh.payload.dto.UserDTO;
import com.zosh.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String email, String password) throws Exception;
    AuthResponse signup(UserDTO req) throws Exception;
}
