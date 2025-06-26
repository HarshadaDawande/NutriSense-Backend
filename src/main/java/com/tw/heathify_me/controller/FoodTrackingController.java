package com.tw.heathify_me.controller;

import com.tw.heathify_me.dto.FoodTrackingDTO;
import com.tw.heathify_me.repository.FoodTracking.FoodTrackingDocument;
import com.tw.heathify_me.repository.User.UserDocument;
import com.tw.heathify_me.service.FoodTrackingService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/v1")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class FoodTrackingController {
    private static final Logger logger = LoggerFactory.getLogger(FoodTrackingController.class);

    final FoodTrackingService foodTrackingService;

    public FoodTrackingController(FoodTrackingService foodTrackingService) {
        this.foodTrackingService = foodTrackingService;
    }

    @GetMapping(value = "/greeting")
    public String greeting() {
        return "Hello Harshada !";
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> logMeal(@RequestBody FoodTrackingDTO foodTrackingDTO) {
        logger.info("Food DTO : " + foodTrackingDTO);
        FoodTrackingDocument foodTrackingDocument = FoodTrackingDocument.builder()
                .mealType(foodTrackingDTO.getMealType())
                .mealDescription(foodTrackingDTO.getMealDescription())
                .proteins(foodTrackingDTO.getProteins())
                .carbs(foodTrackingDTO.getCarbs())
                .fats(foodTrackingDTO.getFats())
                .calories(foodTrackingDTO.getCalories())
                .mealDate(foodTrackingDTO.getMealDate())
                .userId(foodTrackingDTO.getUserId())
                .mealId(foodTrackingDTO.getMealId())
                .build();
        foodTrackingService.save(foodTrackingDocument);

        return ResponseEntity.ok().body("Meal logged successfully!");
    }

    @GetMapping("/{userId}/meals")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> getMealsForTheUser(@PathVariable String userId) {
        var mealsDocument = foodTrackingService.getMealsForTheUser(userId);
        if (mealsDocument == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meals not logged");
        }
        return ResponseEntity.ok(mealsDocument);
    }

    @DeleteMapping("/meals/{mealId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> deleteMeal(@PathVariable String mealId) {
        var mealsDocument = foodTrackingService.findByMealId(mealId);
        if (mealsDocument == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found");
        }
        foodTrackingService.deleteMeal(mealId);
        return ResponseEntity.ok(mealsDocument);
    }
}
