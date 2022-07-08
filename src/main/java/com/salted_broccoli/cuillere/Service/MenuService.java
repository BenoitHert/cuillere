package com.salted_broccoli.cuillere.Service;


import com.salted_broccoli.cuillere.Model.Menu.Dish;
import com.salted_broccoli.cuillere.Model.Menu.Ingredient;
import com.salted_broccoli.cuillere.Model.Menu.Meal;
import com.salted_broccoli.cuillere.Model.Menu.Recipe;
import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Repository.IngredientRepository;
import com.salted_broccoli.cuillere.Repository.MealRepository;
import com.salted_broccoli.cuillere.Repository.RecipeRepository;
import com.salted_broccoli.cuillere.Repository.UserRepository;
import com.salted_broccoli.cuillere.Service.form.IngredientForm;
import com.salted_broccoli.cuillere.Service.form.MealForm;
import com.salted_broccoli.cuillere.Service.form.RecipeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service("Menu Service")
public class MenuService {

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    MealRepository mealRepository;
    @Autowired
    UserRepository userRepository;

//    TODO Couverture de tests

    public User findUser() {
        //Spring Security-side identifier; tied to the user's session => user's email
        String connectedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        //Fetch according to the email
        return userRepository.findUserByEmail(connectedUserEmail);
    }

    public Ingredient createIngredient(IngredientForm form){
//      TODO  CHECK IF INGREDIENT IS ALREADY IN DB
        Ingredient ingredient = new Ingredient();
        ingredient.setAliment(form.getAliment());
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id){
        Ingredient ingredient = ingredientRepository.findIngredientById(id);
        ingredientRepository.delete(ingredient);
    }

    public void addIngredientToRecipe(Recipe recipe, Ingredient ingredient){
        recipe.getIngredients().add(ingredient);
    }

    public void removeIngredientFromRecipe(Recipe recipe, Ingredient ingredient){
        recipe.getIngredients().remove(ingredient);
    }

    public Recipe createRecipe(RecipeForm form){

        Recipe recipe = new Recipe();
        for (Ingredient i : form.getIngredients()){addIngredientToRecipe(recipe, i);}
        recipe.setDescription(form.getDescription());
        recipe.setDishtype(form.getDishtype());
        recipe.setDuration(form.getDuration());
        recipe.setCookingTime(form.getCookingTime());
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long id){
        User user = findUser();
        Recipe recipe = recipeRepository.findRecipeByid(id);
        recipeRepository.delete(recipe);
//        TODO delete recipes from user ?


    }

    public Meal addRecipeToMeal(Meal meal, Recipe recipe){

        if (recipe.getDishtype() == Dish.APPETIZER){meal.setAppetizer(recipe);}
        if(recipe.getDishtype() == Dish.MAINDISH){meal.setMaindish(recipe);}
        if (recipe.getDishtype() == Dish.DESERT){meal.setDesert(recipe);}
        else {throw new ArithmeticException("Erreur dans la confection du repas");}

        return mealRepository.save(meal);
    }

    public Meal deleteRecipeFromMean(Meal meal, Recipe recipe){
        if (recipe.getDishtype() == Dish.APPETIZER){meal.setAppetizer(null);}
        if(recipe.getDishtype() == Dish.MAINDISH){meal.setMaindish(null);}
        if (recipe.getDishtype() == Dish.DESERT){meal.setDesert(null);}
        else {throw new ArithmeticException("Erreur dans la confection du repas");}

        return mealRepository.save(meal);
    }

    public User addMeal(Meal meal){
        User user = findUser();
        user.getMeals().add(meal);
        return userRepository.save(user);

    }

    public Meal createMeal(MealForm form){
        Meal meal = new Meal();
        meal.setAppetizer(form.getAppetizer());
        meal.setMaindish(form.getMaindish());
        meal.setDesert(form.getDesert());
        return mealRepository.save(meal);
    }

    public User deleteMeal(Long id){
        User user = findUser();
        Meal meal = mealRepository.findMealById(id);
        user.getMeals().remove(meal);
        mealRepository.delete(meal);
        return userRepository.save(user);
    }
    public User removeMealFromList(Long id){
        User user = findUser();
        Meal meal = mealRepository.findMealById(id);
        user.getMeals().remove(meal);
        return userRepository.save(user);
    }
}
