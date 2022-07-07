package com.salted_broccoli.cuillere.Controller;

import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Repository.UserRepository;
import com.salted_broccoli.cuillere.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("menu")
    public ModelAndView menuPage(Model model){
        User user = userService.findUser();
        model.addAttribute("user", user);
        return new ModelAndView("menu");
    }
}
