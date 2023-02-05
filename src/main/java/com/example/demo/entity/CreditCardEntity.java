package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table( name = "kartica")
@Data
public class CreditCardEntity {

    @Column( name = "karticaid")
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer  cardId;

    @Column( name = "broj_kartice")
    @NotBlank
    private String cardNumber;

    @Column( name = "vrsta_kartice")
    @NotBlank
    private String cardType;

    @Column( name = "datum_isteka")
    @NotBlank
    private LocalDate expireDate;

    @Column( name = "cvv_broj")
    @NotBlank
    private Integer cvvNumber;

    @Column( name = "korisnikid")
    @NotBlank
    private Integer userId;
}
