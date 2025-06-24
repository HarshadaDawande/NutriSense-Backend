package com.tw.nutrisense.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTrackingRepository extends MongoRepository<FoodTrackingDocument, Integer> {
}
