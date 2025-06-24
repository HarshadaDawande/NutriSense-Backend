package com.tw.nutrisense.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDocument, Long> {
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}
