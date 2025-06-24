package com.tw.heathify_me.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.heathify_me.dto.CreateUserDTO;
import com.tw.heathify_me.repository.User.UserDocument;
import com.tw.heathify_me.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /v1/user returns 201 OK on success")
    void createUser_success() throws Exception {
        CreateUserDTO dto = CreateUserDTO.builder()
                .userName("Bob")
                .emailAddress("bob@example.com")
                .build();

        mockMvc.perform(post("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("User created successfully")));
    }

    @Test
    @DisplayName("POST /v1/user returns 409 when conflict")
    void createUser_conflict() throws Exception {
        CreateUserDTO dto = CreateUserDTO.builder()
                .userName("Bob")
                .emailAddress("bob@example.com")
                .build();

        doThrow(new ResponseStatusException(HttpStatus.CONFLICT,"User already exists"))
                .when(userService).create(any());

        mockMvc.perform(post("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", containsString("User already exists")));
    }

    @Test
    @DisplayName("GET /v1/user/{email} returns user or 404")
    void getUser() throws Exception {
        UserDocument doc = UserDocument.builder()
                .userName("Carol")
                .emailAddress("carol@example.com")
                .build();

        when(userService.getUser("carol@example.com")).thenReturn(doc);

        mockMvc.perform(get("/v1/user/{email}", "carol@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Carol"));

        when(userService.getUser("carol@example.com")).thenReturn(null);

        mockMvc.perform(get("/v1/user/{email}", "carol@example.com"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", containsString("User not found")));
    }
}
