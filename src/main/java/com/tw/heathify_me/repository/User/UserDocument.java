package com.tw.heathify_me.repository.User;

import java.time.Instant;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.CreatedDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Users")
public class UserDocument {
    @Id
    private ObjectId id;
    private String userName;
    private String emailAddress;
    private String isFirstTimeUser = "true";
    private String hasSetTargets = "false";
    @CreatedDate
    private Instant createdAt;
    @UUID
    private String userId;
}
