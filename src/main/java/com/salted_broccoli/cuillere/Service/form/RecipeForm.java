package com.salted_broccoli.cuillere.Service.form;

import com.salted_broccoli.cuillere.Model.Menu.Dish;
import com.salted_broccoli.cuillere.Model.Menu.Ingredient;
import lombok.Data;

import java.util.List;

@Data
public class RecipeForm {
    private List<Ingredient> ingredients;
    private String duration;
    private String cookingTime;
    private Dish dishtype;
    private String description;
}
