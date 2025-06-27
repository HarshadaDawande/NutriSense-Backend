package com.tw.heathify_me.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tw.heathify_me.dto.CreateUserDTO;
import com.tw.heathify_me.repository.User.UserDocument;
import com.tw.heathify_me.repository.User.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(CreateUserDTO createUserDTO) {
        var existing = userRepository.findByEmailAddress(createUserDTO.getEmailAddress());
        if (existing.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists with that email address");
        }

        var userDocument = UserDocument.builder()
                .userName(createUserDTO.getUserName())
                .emailAddress(createUserDTO.getEmailAddress())
                .isFirstTimeUser(createUserDTO.getIsFirstTimeUser())
                .hasSetTargets(createUserDTO.getHasSetTargets())
                .userId(createUserDTO.getUserId())
                .build();
        logger.info("User created with name " + userDocument.getUserName());
        userRepository.save(userDocument);
    }

    public UserDocument getUser(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress).orElse(null);
    }

    public void updateHasSetTargets(String emailAddress, String hasSetTargets) {
        userRepository.findByEmailAddress(emailAddress)
            .ifPresent(user -> {
                logger.info("Id for user ", user.getId());
                user.setHasSetTargets(hasSetTargets);
                userRepository.save(user);
            });
    }
}
