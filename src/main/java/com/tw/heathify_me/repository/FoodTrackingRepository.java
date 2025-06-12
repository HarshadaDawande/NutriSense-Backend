package com.tw.heathify_me.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTrackingRepository extends MongoRepository<FoodTrackingDocument, Integer> {

}
