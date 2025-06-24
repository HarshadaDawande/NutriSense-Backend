package com.tw.heathify_me.controller;

import org.springframework.web.bind.annotation.RestController;
import com.tw.heathify_me.dto.CreateUserDTO;
import com.tw.heathify_me.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping(value = "/v1/user")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        return ResponseEntity.ok().body("User created successfully!");
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<String> getUser(@PathVariable String id) {
        return ResponseEntity.ok().body("User retrieved successfully!");
    }
}
