package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table( name = "placanje")
@Data
public class PaymentEntity {

    @Column( name = "placanjeid")
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer paymentId;

    @Column( name = "iznos")
    private double amount;

    @Column( name = "korisnikid")
    private Integer userId;

    @Column( name = "karticaid")
    private Integer cardId;

    @Column( name = "datum_placanja")
    @CreationTimestamp
    private Timestamp dateOfPayment;

}
