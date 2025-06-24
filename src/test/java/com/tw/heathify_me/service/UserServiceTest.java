package com.tw.heathify_me.service;

import com.tw.heathify_me.dto.CreateUserDTO;
import com.tw.heathify_me.repository.User.UserDocument;
import com.tw.heathify_me.repository.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private CreateUserDTO createUserDTO;

    @BeforeEach
    void setUp() {
        createUserDTO = CreateUserDTO.builder()
                .userName("John Doe")
                .emailAddress("john@example.com")
                .build();
    }

    @Test
    @DisplayName("create() should throw CONFLICT when email already exists")
    void createUser_whenEmailExists_throwsConflict() {
        when(userRepository.findByEmailAddress(createUserDTO.getEmailAddress()))
                .thenReturn(Optional.of(UserDocument.builder().build()));

        assertThatThrownBy(() -> userService.create(createUserDTO))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("User already exists")
                .extracting("statusCode")
                .isEqualTo(HttpStatus.CONFLICT);

        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("create() should persist user when email is unique")
    void createUser_success() {
        when(userRepository.findByEmailAddress(createUserDTO.getEmailAddress()))
                .thenReturn(Optional.empty());

        userService.create(createUserDTO);

        ArgumentCaptor<UserDocument> captor = ArgumentCaptor.forClass(UserDocument.class);
        verify(userRepository).save(captor.capture());

        UserDocument saved = captor.getValue();
        assertThat(saved.getUserName()).isEqualTo(createUserDTO.getUserName());
        assertThat(saved.getEmailAddress()).isEqualTo(createUserDTO.getEmailAddress());
    }

    @Test
    @DisplayName("getUser() should return user document or null")
    void getUser_returnsExpected() {
        UserDocument doc = UserDocument.builder()
                .userName("Alice")
                .emailAddress("alice@example.com")
                .build();
        when(userRepository.findByEmailAddress("alice@example.com"))
                .thenReturn(Optional.of(doc))
                .thenReturn(Optional.empty());

        assertThat(userService.getUser("alice@example.com")).isEqualTo(doc);
        assertThat(userService.getUser("alice@example.com")).isNull();
    }
}
