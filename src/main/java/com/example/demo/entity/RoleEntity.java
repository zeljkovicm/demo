package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table( name = "rola")
@Data
public class RoleEntity {

    @Column( name = "rolaid")
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer  roleId;

    @Column( name = "naziv_rola")
    private String roleName;

}
