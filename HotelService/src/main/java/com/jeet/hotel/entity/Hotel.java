package com.jeet.hotel.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "HOTELS") // MongoDB collection name
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {

    @Id
    private String id;

    private String name;

    private String location;

    private String about;
}
