package com.salted_broccoli.cuillere.Controller;

import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Repository.UserRepository;
import com.salted_broccoli.cuillere.Service.ShopService;
import com.salted_broccoli.cuillere.Service.UserService;
import com.salted_broccoli.cuillere.Service.form.ShopForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShopController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;



    @GetMapping("shop")
    public ModelAndView shopPage(Model model, ShopForm form){
        User user = userService.findUser();
        model.addAttribute("user", user);
        model.addAttribute("shopForm", new ShopForm());
        return new ModelAndView("shop");
    }

    @PostMapping("shop/add")
    public ModelAndView addShopItem(Model model, @ModelAttribute("shopForm") ShopForm form){
        User user = userService.findUser();
        model.addAttribute("user", user);
        model.addAttribute("shopForm", new ShopForm());
        try{shopService.addItemToList(form);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("shop");
        }
        return new ModelAndView("shop");
    }

    @PostMapping("shop/delete/{id}")
    public ModelAndView deleteShopItem(Model model, @PathVariable Long id, @ModelAttribute("shopForm") ShopForm form){
        User user = userService.findUser();
        model.addAttribute("user", user);
        model.addAttribute("shopForm", new ShopForm());
        try{shopService.removeItemFromList(id);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("shop");
        }
        return new ModelAndView("shop");
    }
}
