package com.tw.heathify_me.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing         // turns on @CreatedDate, @LastModifiedDate, etc.
public class MongoConfig { }