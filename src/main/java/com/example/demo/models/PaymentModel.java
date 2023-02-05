package com.example.demo.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentModel {

    private Integer  paymentId;
    private double amount;
    private Integer userId;
    private Integer cardId;
    private Timestamp dateOfPayment;

}
