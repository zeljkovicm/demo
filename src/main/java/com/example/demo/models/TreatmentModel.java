package com.example.demo.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.concurrent.atomic.LongAccumulator;

@Data
public class TreatmentModel {

        private Integer  treatmentId;
        private String treatmentName;
        private String treatmentDescription;
        private Integer userId;
        private double price;
        private String imgUrl;
        private Integer categoryId;
        private Timestamp timeOfCreation;

}

