package com.example.demo.models;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@Data
public class UserWithReservationsModel {

    private Integer  userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String address;
    private String state;
    private String email;
    private String password;
    private Integer roleId;
    private Timestamp timeOfCreation;
    private List<ReservationModel> reservations;
}
