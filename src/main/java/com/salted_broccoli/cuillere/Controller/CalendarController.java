package com.salted_broccoli.cuillere.Controller;

import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Repository.UserRepository;
import com.salted_broccoli.cuillere.Service.CalendarService;
import com.salted_broccoli.cuillere.Service.UserService;
import com.salted_broccoli.cuillere.Service.form.EventForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendarController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    CalendarService calendarService;

    @GetMapping("calendar")
    public ModelAndView calendarPage(Model model){
        User user = userService.findUser();
        model.addAttribute("user", user);
        return new ModelAndView("calendar");
    }

    @PostMapping("calendar/addEvent")
    public ModelAndView addEvent(Model model, @ModelAttribute("eventForm")EventForm form){
        User user = userService.findUser();
        model.addAttribute("user", user);

        try{calendarService.addEventToCalendar(form);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("calendar");
        }
        return new ModelAndView("calendar");

    }
}
