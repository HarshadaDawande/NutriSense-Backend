package com.tw.nutrisense.repository.User;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserDocument, ObjectId> {
    Optional<UserDocument> findByEmailAddress(String emailAddress);
}
