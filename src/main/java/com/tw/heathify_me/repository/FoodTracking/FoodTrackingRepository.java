package com.tw.heathify_me.repository.FoodTracking;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FoodTrackingRepository extends MongoRepository<FoodTrackingDocument, String> {
    Optional<List<FoodTrackingDocument>> findByUserId(String userId);
    void deleteByMealId(String mealId);
    Optional<FoodTrackingDocument> findByMealId(String mealId);
}
