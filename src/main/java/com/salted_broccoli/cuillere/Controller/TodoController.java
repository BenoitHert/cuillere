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
        todoService.itemSave(form);
        model.addAttribute("user", user);
        model.addAttribute("todoItems", todoItems);
        return new ModelAndView("todo");

    }

    @PutMapping("todoDone/{id}")
    public ModelAndView itemDone(@PathVariable Long id, Model model){
        todoService.itemUpdate(id, false);
        User user = userService.findUser();
        List <TodoItem> todoItems = todoService.findItems();
        model.addAttribute("user", user);
        model.addAttribute("todoItems", todoItems);
        return new ModelAndView("todo");
    }

    @PutMapping("todoNotDone/{id}")
    public ModelAndView itemNotDone(@PathVariable Long id, Model model){
        todoService.itemUpdate(id, false);
        User user = userService.findUser();
        List <TodoItem> todoItems = todoService.findItems();
        model.addAttribute("user", user);
        model.addAttribute("todoItems", todoItems);
        return new ModelAndView("todo");
    }

    @DeleteMapping(value = "todo/{id}")
    public void delete(@PathVariable Long id){
        todoRepository.deleteById(id);
    }

}
