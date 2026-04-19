package com.zosh.service.impl;
import com.zosh.config.JwtProvider;
import com.zosh.enums.UserRole;
import com.zosh.mapper.UserMapper;
import com.zosh.model.User;
import com.zosh.payload.dto.UserDTO;
import com.zosh.payload.response.AuthResponse;
import com.zosh.repository.UserRepository;
import com.zosh.service.AuthService;
import com.zosh.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtProvider jwtProvider;
    /*
        * 1. Check if email already exists
        * 2. Encode password using BCrypt
        * 3. Save user in database
        * 4. Generate JWT token
        * 5. Return token and user information
    * */

    @Override
    public AuthResponse signup(UserDTO req) throws Exception {
        User existingUser = userRepository.findByEmail(req.getEmail());
        if(existingUser != null){
            throw new Exception("email already registered!");
        }

        if(req.getRole() == UserRole.ROLE_SYSTEM_ADMIN){
            throw new Exception("You cannot sing up system admins.");
        }

        User newUser = User.builder()
            .fullName(req.getFullName())
            .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
            .phone(req.getPhone())
            .role(req.getRole())
            .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now()).build();

        User user = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());

        String jwt = jwtProvider.generateToken(authentication, user.getId());

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setUser(UserMapper.toDTO(user));
        response.setTitle("Welcome " + user.getFullName());
        response.setMessage("Registered successfully!");
        return response;
    }

    /*
    * 1. Load user by email
    * 2. Compare password with BCrypt
    * 3. Update 'lastlogin' time
    * 4. Generate JWT token
    * 5. Return token and user information
    * */

    @Override
    public AuthResponse login(String email, String password) throws Exception {
        Authentication authentication = authenticate(email, password);

        User user = userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        String jwt = jwtProvider.generateToken(authentication, user.getId());

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setUser(UserMapper.toDTO(user));
        response.setTitle("Welcome " + user.getFullName());
        response.setMessage("Login successfully!");
        return response;
    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new Exception("invalid password!");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
    }
}
