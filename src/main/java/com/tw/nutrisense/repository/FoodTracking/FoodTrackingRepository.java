package com.tw.nutrisense.repository.FoodTracking;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FoodTrackingRepository extends MongoRepository<FoodTrackingDocument, ObjectId> {
    Optional<List<FoodTrackingDocument>> findByUserId(String userId);
    void deleteByMealId(String mealId);
    Optional<FoodTrackingDocument> findByMealId(String mealId);
}
