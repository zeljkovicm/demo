package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table( name = "korisnik")
@Data
public class UserWithReservationsEntity {

    @Column( name = "korisnikid")
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer  userId;

    @Column( name = "ime")
    private String firstName;

    @Column( name = "prezime")
    private String lastName;

    @Column( name = "pol")
    private String gender;

    @Column( name = "telefon")
    private String phoneNumber;

    @Column( name = "adresa")
    private String address;

    @Column( name = "drzava")
    private String state;

    @Column( name = "email" )
    @NotBlank
    @Email
    private String email;

    @Column(name="sifra")
    private String password;

    @Column( name = "rolaid")
    private Integer roleId;

    @Column( name = "vreme_kreiranja")
    @CreationTimestamp
    private Timestamp timeOfCreation;

    @OneToMany( mappedBy = "userId")
    private List<ReservationEntity> reservations;


}
