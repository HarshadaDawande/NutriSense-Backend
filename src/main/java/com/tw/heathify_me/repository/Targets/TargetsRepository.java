package com.tw.heathify_me.repository.Targets;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetsRepository extends MongoRepository<TargetsDocument, String> {
}
