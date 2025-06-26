package com.tw.heathify_me.repository.Targets;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "Targets")
public class TargetsDocument {
    @Id
    private ObjectId id;
    private String userName;
    private String createdAt;
    private String protein;
    private String carbs;
    private String fats;
    private String calories;
}
