package com.salted_broccoli.cuillere.Model.Menu;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Ingredient {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String aliment;
}
