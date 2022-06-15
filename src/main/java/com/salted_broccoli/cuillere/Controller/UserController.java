package com.salted_broccoli.cuillere.Controller;

import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Service.UserService;
import com.salted_broccoli.cuillere.Service.form.LoginForm;
import com.salted_broccoli.cuillere.Service.form.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public ModelAndView registerSubmit(@ModelAttribute("registrationForm") RegistrationForm form) {
        userService.registration(form);
        return new ModelAndView("login", "loginForm", new LoginForm());
    }
    @GetMapping("/")
    public ModelAndView home(){
        User user = userService.findUser();
        return new ModelAndView("index", "user", user);
    }


    @RequestMapping(value = "calendar", method = RequestMethod.GET)
    public ModelAndView calendar(){
        return new ModelAndView("calendar");
    }
}