package com.tw.heathify_me.repository.User;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Data;  
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Users")
public class UserDocument {
    private String userName;
    private String emailAddress;
    private ZonedDateTime createdAt;
}