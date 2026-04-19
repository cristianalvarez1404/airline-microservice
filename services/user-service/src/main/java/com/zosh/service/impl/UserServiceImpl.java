package com.zosh.service.impl;

import com.zosh.mapper.UserMapper;
import com.zosh.model.User;
import com.zosh.payload.dto.UserDTO;
import com.zosh.repository.UserRepository;
import com.zosh.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDTO getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("user not found with email" + email);
        }

        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("user not found with id " + id));

        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toUnmodifiableList());
    }
}
