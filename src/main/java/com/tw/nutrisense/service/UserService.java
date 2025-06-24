package com.tw.nutrisense.service;

import com.tw.nutrisense.dto.UserDTO;
import com.tw.nutrisense.exception.UserAlreadyExistsException;
import com.tw.nutrisense.repository.UserDocument;
import com.tw.nutrisense.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(UserDTO userDTO) {
        if (userRepository.existsByName(userDTO.getName())) {
            logger.info("User already exists: ", userDTO.getName());
            throw new UserAlreadyExistsException("User already exists: " + userDTO.getName());
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            logger.info("Email already exists: ", userDTO.getEmail());
            throw new UserAlreadyExistsException("Email already exists: " + userDTO.getEmail());
        }

        UserDocument userDocument = UserDocument.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();

        userRepository.save(userDocument);
        logger.info("User created successfully: {}", userDTO.getName());
    }
}
