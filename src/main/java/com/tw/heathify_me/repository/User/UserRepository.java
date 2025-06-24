package com.tw.heathify_me.repository.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDocument, String> {
    
}
