package com.tw.heathify_me.service;

import com.tw.heathify_me.repository.FoodTracking.FoodTrackingDocument;
import com.tw.heathify_me.repository.FoodTracking.FoodTrackingRepository;

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
}
