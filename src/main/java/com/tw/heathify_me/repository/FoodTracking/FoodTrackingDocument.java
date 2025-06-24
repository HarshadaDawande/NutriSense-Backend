package com.tw.heathify_me.repository.FoodTracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "FoodMacros")
public class FoodTrackingDocument {
    private String mealType;
    private String mealDescription;
    private String proteins;
    private String carbs;
    private String fats;
    private String calories;
    private String mealDate;
}
