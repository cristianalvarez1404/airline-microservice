package com.zosh.service;
import com.zosh.payload.dto.UserDTO;
import com.zosh.payload.response.AuthResponse;
import com.zosh.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthRepository authRepository;

    @Override
    public AuthResponse login(String email, String password) {
        return null;
    }

    @Override
    public AuthResponse signup(UserDTO req) {
        return null;
    }
}
