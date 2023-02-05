package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table( name = "tretman")
@Data
public class TreatmentWithCategoryEntity {

    @Column( name = "tretmanid")
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer  treatmentId;

    @Column( name = "naziv_tretmana")
    private String treatmentName;

    @Column( name = "opis_tretmana")
    private String treatmentDescription;

    @Column( name = "korisnikid" )
    private Integer userId;

    @Column( name = "cena" )
    private double price;

    @Column( name = "slika_url" )
    private String imgUrl;

    @Column( name = "kategorija_tretmanaid" )
    private Integer categoryId;

    @Column( name = "VremeKreiranja" )
    @CreationTimestamp
    private Timestamp timeOfCreation;

    @ManyToOne(optional=false)
    @JoinColumn( name="kategorija_tretmanaid", referencedColumnName="kategorija_tretmanaid", insertable=false, updatable=false)
    private CategoryEntity category;
}
