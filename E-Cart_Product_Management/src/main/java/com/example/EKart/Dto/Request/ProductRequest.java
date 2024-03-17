package com.example.EKart.Dto.Request;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

@Data
public class ProductRequest {

    @NotBlank(message = "No null values allowed")
    private String ProductName;

    @NotBlank(message = "no null values allowed")
    private String ProductDescription;

    private String ProductType;
}
