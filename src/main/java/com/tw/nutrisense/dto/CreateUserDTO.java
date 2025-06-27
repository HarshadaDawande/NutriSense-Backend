package com.tw.nutrisense.dto;

import jakarta.validation.constraints.*;   // javax.validation.* for Spring ≤2.7
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDTO {

    @NotBlank(message = "User name is required")
    private String userName;

    @NotBlank(message = "Email address is required")
    @Email(message = "Email address is invalid")
    private String emailAddress;
    private String isFirstTimeUser = "true";
    private String hasSetTargets = "false";

    @NotBlank(message = "User ID is required")
    private String userId;
}
