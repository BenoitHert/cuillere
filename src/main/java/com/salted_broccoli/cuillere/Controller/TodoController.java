package com.salted_broccoli.cuillere.Controller;

import com.salted_broccoli.cuillere.Model.TodoItem;
import com.salted_broccoli.cuillere.Repository.TodoRepository;
import com.salted_broccoli.cuillere.Service.TodoService;
import com.salted_broccoli.cuillere.Service.form.ItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "todo")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoItem> findAll(){
        return todoRepository.findAll();
    }

    @PostMapping
    public TodoItem itemSave(@RequestBody ItemForm form){
        return todoService.itemSave(form);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        todoRepository.deleteById(id);
    }

}
