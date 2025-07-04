package com.tw.nutrisense.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TargetsDTO {
    private String userName;
    private String emailAddress;
    private String createdAt;
    private String protein;
    private String carbs;
    private String fats;
    private String calories;
}
