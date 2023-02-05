package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table( name = "kategorija_tretmana")
@Data
public class CategoryEntity {

    @Column( name = "kategorija_tretmanaid")
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )  // Na ovaj nacin stavljamo da je id auto_increment
    private Integer  categoryId;

    @Column( name = "naziv_kategorije")
    private String categoryName;
}
