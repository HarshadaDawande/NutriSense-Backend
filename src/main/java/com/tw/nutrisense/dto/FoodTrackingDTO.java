package com.tw.nutrisense.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodTrackingDTO {
    private String mealType;
    private String mealDescription;
    private String proteins;
    private String carbs;
    private String fats;
    private String calories;
    private String mealDate;
}
