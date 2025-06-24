package com.tw.nutrisense.service;

import com.tw.nutrisense.dto.FoodTrackingDTO;
import com.tw.nutrisense.repository.FoodTrackingDocument;
import com.tw.nutrisense.repository.FoodTrackingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FoodTrackingService {
    private static final Logger logger = LoggerFactory.getLogger(FoodTrackingService.class);
    final FoodTrackingRepository foodTrackingRepository;

    public FoodTrackingService(FoodTrackingRepository foodTrackingRepository) {
        this.foodTrackingRepository = foodTrackingRepository;
    }

    public void save(FoodTrackingDTO foodTrackingDTO) {
        FoodTrackingDocument foodTrackingDocument = FoodTrackingDocument.builder()
                .mealType(foodTrackingDTO.getMealType())
                .mealDescription(foodTrackingDTO.getMealDescription())
                .proteins(foodTrackingDTO.getProteins())
                .carbs(foodTrackingDTO.getCarbs())
                .fats(foodTrackingDTO.getFats())
                .calories(foodTrackingDTO.getCalories())
                .mealDate(foodTrackingDTO.getMealDate())
                .build();
        logger.info("Saved meal details for " + foodTrackingDocument.getMealType());
        foodTrackingRepository.save(foodTrackingDocument);
    }
}
