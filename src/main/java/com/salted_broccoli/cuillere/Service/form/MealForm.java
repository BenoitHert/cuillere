package com.salted_broccoli.cuillere.Service.form;

import com.salted_broccoli.cuillere.Model.Menu.Recipe;
import lombok.Data;

@Data
public class MealForm {
    private Recipe appetizer;
    private Recipe maindish;
    private Recipe desert;
}
