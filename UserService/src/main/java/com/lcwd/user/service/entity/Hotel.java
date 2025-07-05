package com.lcwd.user.service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

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
