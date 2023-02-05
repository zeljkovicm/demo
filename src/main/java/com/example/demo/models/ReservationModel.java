package com.example.demo.models;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Data
public class ReservationModel {

    private Integer  reservationId;
    private Integer userId;
    private Integer treatmentId;
    private double amount;
    private String message;
    private LocalDate date;
    private Timestamp timeOfCreation;

}
