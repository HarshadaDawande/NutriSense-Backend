package com.tw.heathify_me.controller;

import com.tw.heathify_me.dto.FoodTrackingDTO;
import com.tw.heathify_me.repository.FoodTrackingDocument;
import com.tw.heathify_me.service.FoodTrackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> saveFood(@RequestBody FoodTrackingDTO foodTrackingDTO) {
        logger.info("Food DTO : " + foodTrackingDTO);
        FoodTrackingDocument foodTrackingDocument = FoodTrackingDocument.builder()
                .mealType(foodTrackingDTO.getMealType())
                .mealDescription(foodTrackingDTO.getMealDescription())
                .proteins(foodTrackingDTO.getProteins())
                .carbs(foodTrackingDTO.getCarbs())
                .fats(foodTrackingDTO.getFats())
                .calories(foodTrackingDTO.getCalories())
                .mealDate(foodTrackingDTO.getMealDate())
                .build();
        foodTrackingService.save(foodTrackingDocument);

        return ResponseEntity.ok().body("Food tracking data saved successfully!");
    }
}
