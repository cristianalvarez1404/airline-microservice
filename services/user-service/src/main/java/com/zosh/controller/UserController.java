package com.zosh.controller;

import com.zosh.model.User;
import com.zosh.payload.dto.UserDTO;
import com.zosh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(
            @RequestHeader("X-User-Email") String email) throws Exception {
        UserDTO user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }


}
