package com.auto1.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String id;
    private String manufacture;
    private String model;
    private String imageUrl;
    private String userId;
}
