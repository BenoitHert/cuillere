package com.salted_broccoli.cuillere.Controller;

import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Repository.UserRepository;
import com.salted_broccoli.cuillere.Service.MenuService;
import com.salted_broccoli.cuillere.Service.UserService;
import com.salted_broccoli.cuillere.Service.form.IngredientForm;
import com.salted_broccoli.cuillere.Service.form.MealForm;
import com.salted_broccoli.cuillere.Service.form.RecipeForm;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    @GetMapping("menu")
    public ModelAndView menuPage(Model model){
        User user = userService.findUser();
        model.addAttribute("user", user);
        return new ModelAndView("menu");
    }

    @PostMapping("menu/ingredient/create")
    public ModelAndView createIngredient(Model model, @ModelAttribute("ingredientForm")IngredientForm form){
        User user = userService.findUser();
        model.addAttribute("user", user);
        try{menuService.createIngredient(form);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("menu");
        }
        return new ModelAndView("menu");
    }

    @PostMapping("menu/recipe/create")
    public ModelAndView createRecipe(Model model, @ModelAttribute("recipeForm")RecipeForm form){
        User user = userService.findUser();
        model.addAttribute("user", user);
        try{menuService.createRecipe(form);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("menu");
        }
        return new ModelAndView("menu");
    }

    @PostMapping("menu/ingredient/delete/{id}")
    public ModelAndView deleteIngredient(Model model, @PathVariable Long id){
        User user = userService.findUser();
        model.addAttribute("user", user);
//        TODO remove ingredient from every list it is on when deleted
        try{menuService.deleteIngredient(id);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("menu");
        }
        return new ModelAndView("menu");
    }

    @PostMapping("menu/recipe/delete/{id}")
    public ModelAndView deleteRecipe(Model model, @PathVariable Long id){
        User user = userService.findUser();
        model.addAttribute("user", user);
        try{menuService.deleteRecipe(id);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("menu");
        }
        return new ModelAndView("menu");
    }

    @PostMapping("menu/meal/create")
    public ModelAndView addMeal(Model model, @ModelAttribute("mealForm") MealForm form){
        User user = userService.findUser();
        model.addAttribute("user", user);
        try{menuService.createMeal(form);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("menu");
        }
        return new ModelAndView("menu");
    }

    @PostMapping("menu/meal/delete/{id}")
    public ModelAndView deleteMeal(Model model, @PathVariable Long id){
        User user = userService.findUser();
        model.addAttribute("user", user);
        try{menuService.deleteMeal(id);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("menu");
        }
        return new ModelAndView("menu");
    }



}
