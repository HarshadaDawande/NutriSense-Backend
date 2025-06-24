package com.tw.nutrisense.controller;

import com.tw.nutrisense.dto.ApiResponse;
import com.tw.nutrisense.dto.UserDTO;
import com.tw.nutrisense.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody UserDTO userDTO) {
        logger.info("New user sign-up: {}", userDTO.getName());
        userService.signup(userDTO);
        return ResponseEntity.ok(ApiResponse.success("User created successfully!"));
    }
}
