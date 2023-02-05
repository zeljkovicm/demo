package com.example.demo.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TreatmentWithCategoryModel {

    private Integer  treatmentId;
    private String treatmentName;
    private String treatmentDescription;
    private Integer userId;
    private double price;
    private String imgUrl;
    private Integer categoryId;
    private Timestamp timeOfCreation;
    private CategoryModel category;
}
