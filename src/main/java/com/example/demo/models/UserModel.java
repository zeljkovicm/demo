package com.example.demo.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
public class UserModel {

    private Integer userId;
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

}
