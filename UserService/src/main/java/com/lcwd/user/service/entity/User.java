package com.lcwd.user.service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "USERS") // MongoDB collection name
public class User {

    @Id
    private String userId;

    private String name;
    private String email;
    private String about;

    @Transient // Spring Data MongoDB's Transient
    private List<Rating> ratings = new ArrayList<>();
}
