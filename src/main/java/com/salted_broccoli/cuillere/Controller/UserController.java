package com.salted_broccoli.cuillere.Controller;

import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Service.UserService;
import com.salted_broccoli.cuillere.Service.form.LoginForm;
import com.salted_broccoli.cuillere.Service.form.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("register")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("register", "registrationForm", new RegistrationForm());
    }

    @PostMapping("registration")
    public ModelAndView registerSubmit(@ModelAttribute("registrationForm") RegistrationForm form, Model model) {
        try{userService.registration(form);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("register");
        }
        return new ModelAndView("login", "loginForm", new LoginForm());
    }
    @GetMapping("")
    public ModelAndView home(){
        User user = userService.findUser();
        return new ModelAndView("index", "user", user);
    }




//    @DeleteMapping("/{userId}")
//    public void deleteUser(@PathVariable Long id){
//        userService.deleteUser(id);
//    }

}