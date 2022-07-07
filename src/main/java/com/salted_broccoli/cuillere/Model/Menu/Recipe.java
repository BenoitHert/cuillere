package com.salted_broccoli.cuillere.Model.Menu;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List <Ingredient> ingredients;
    private String duration;
    private String cookingTime;
    private Dish dishtype;
    private String description;

}
