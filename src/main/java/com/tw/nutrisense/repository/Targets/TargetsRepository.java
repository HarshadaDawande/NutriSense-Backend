package com.tw.nutrisense.repository.Targets;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetsRepository extends MongoRepository<TargetsDocument, ObjectId> {
}
