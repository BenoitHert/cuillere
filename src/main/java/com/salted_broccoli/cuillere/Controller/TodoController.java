package com.salted_broccoli.cuillere.Controller;

import com.salted_broccoli.cuillere.Model.Todolist.TodoItem;
import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Repository.TodoRepository;
import com.salted_broccoli.cuillere.Service.TodoService;
import com.salted_broccoli.cuillere.Service.UserService;
import com.salted_broccoli.cuillere.Service.form.ItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserService userService;

    @GetMapping("todo")
    public ModelAndView todoPage(Model model, ItemForm itemForm){
        User user = userService.findUser();
        List <TodoItem> todoItems = todoService.findItems();
        model.addAttribute("user", user);
        model.addAttribute("todoItems", todoItems);

        return new ModelAndView("todo");
    }

    @PostMapping("addItem")
    public ModelAndView itemSave(ItemForm form, Model model){
        User user = userService.findUser();
        List <TodoItem> todoItems = todoService.findItems();
        model.addAttribute("user", user);
        model.addAttribute("todoItems", todoItems);
        try{todoService.addToDoItem(form);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("todo");
        }
        return new ModelAndView("todo");

    }

    @PostMapping("todo/Done/{id}")
    public ModelAndView itemDone(@PathVariable Long id, Model model){
        User user = userService.findUser();
        List <TodoItem> todoItems = todoService.findItems();
        model.addAttribute("user", user);
        model.addAttribute("todoItems", todoItems);
        try{todoService.itemUpdate(id, false);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("todo");
        }
        return new ModelAndView("todo");
    }

    @PostMapping("todo/NotDone/{id}")
    public ModelAndView itemNotDone(@PathVariable Long id, Model model){
        User user = userService.findUser();
        List <TodoItem> todoItems = todoService.findItems();
        model.addAttribute("user", user);
        model.addAttribute("todoItems", todoItems);
        try{todoService.itemUpdate(id, false);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("todo");
        }
        return new ModelAndView("todo");
    }

    @PostMapping("todo/delete/{id}")
    public ModelAndView deleteItem(@PathVariable Long id, Model model){
        User user = userService.findUser();
        List <TodoItem> todoItems = todoService.findItems();
        model.addAttribute("user", user);
        model.addAttribute("todoItems", todoItems);
        try{todoService.deleteItem(id);}
        catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return new ModelAndView("todo");

        }
        return new ModelAndView("todo");
    }

}
