package com.example.demo.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreditCardModel {

    private Integer  cardId;
    private String cardNumber;
    private String cardType;
    private LocalDate expireDate;
    private Integer cvvNumber;
    private Integer userId;
}
