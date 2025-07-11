package com.tw.nutrisense.service;

import com.tw.nutrisense.repository.FoodTracking.FoodTrackingDocument;
import com.tw.nutrisense.repository.FoodTracking.FoodTrackingRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FoodTrackingService {
    private static final Logger logger = LoggerFactory.getLogger(FoodTrackingService.class);
    final FoodTrackingRepository foodTrackingRepository;

    public FoodTrackingService(FoodTrackingRepository foodTrackingRepository) {
        this.foodTrackingRepository = foodTrackingRepository;
    }

    public void save(FoodTrackingDocument foodTrackingDocument) {
        logger.info("Saved meal details for " + foodTrackingDocument.getMealType());
        foodTrackingRepository.save(foodTrackingDocument);
    }

    public Object getMealsForTheUser(String userId) {
        return foodTrackingRepository.findByUserId(userId).orElse(null);
    };

    public void deleteMeal(String mealId) {
        foodTrackingRepository.deleteByMealId(mealId);
    }

    public Object findByMealId(String mealId) {
        return foodTrackingRepository.findByMealId(mealId).orElse(null);
    }
}
