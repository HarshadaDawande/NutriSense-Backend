package com.tw.nutrisense.controller;

import com.tw.nutrisense.dto.FoodTrackingDTO;
import com.tw.nutrisense.dto.TargetsDTO;
import com.tw.nutrisense.repository.FoodTracking.FoodTrackingDocument;
import com.tw.nutrisense.repository.Targets.TargetsDocument;
import com.tw.nutrisense.service.FoodTrackingService;
import com.tw.nutrisense.service.TargetsService;
import com.tw.nutrisense.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/v1/meal")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class FoodTrackingController {
    private static final Logger logger = LoggerFactory.getLogger(FoodTrackingController.class);

    final FoodTrackingService foodTrackingService;
    final TargetsService targetsService;
    final UserService userService;

    public FoodTrackingController(FoodTrackingService foodTrackingService, TargetsService targetsService, UserService userService) {
        this.foodTrackingService = foodTrackingService;
        this.targetsService = targetsService;
        this.userService = userService;
    }
    @PostMapping("/targets")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> saveTargets(@RequestBody TargetsDTO targetsDTO) {
        logger.info("Attempting to set daily targets: " + targetsDTO.getEmailAddress());
        TargetsDocument targetsDocument = TargetsDocument.builder()
                .userName(targetsDTO.getUserName())
                .createdAt(targetsDTO.getCreatedAt())
                .protein(targetsDTO.getProtein())
                .carbs(targetsDTO.getCarbs())
                .fats(targetsDTO.getFats())
                .calories(targetsDTO.getCalories())
                .build();
        targetsService.saveTargets(targetsDocument);

        userService.updateHasSetTargets(targetsDTO.getEmailAddress(), "true");

        return ResponseEntity.ok("Targets saved successfully");
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> logMeal(@RequestBody FoodTrackingDTO foodTrackingDTO) {
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

    @DeleteMapping("/{mealId}")
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
