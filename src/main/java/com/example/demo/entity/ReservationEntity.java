package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table( name = "rezervacija")
@Data
public class ReservationEntity {

    @Column( name = "rezervacijaid")
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer  reservationId;

    @Column( name = "korisnikid")
    private Integer userId;

    @Column( name = "tretmanid")
    private Integer treatmentId;

    @Column( name = "iznos")
    private double amount;

    @Column( name = "napomena")
    private String message;

    @Column( name = "datum")
    private LocalDate date;

    @Column( name = "vreme_kreiranja")
    @CreationTimestamp
    private Timestamp timeOfCreation;

}
