package com.salted_broccoli.cuillere.Service;

import com.salted_broccoli.cuillere.Model.TodoItem;
import com.salted_broccoli.cuillere.Repository.TodoRepository;
import com.salted_broccoli.cuillere.Service.form.ItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ToDo Service")
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public TodoItem itemSave(ItemForm form){
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(form.getTitle());
        todoItem.setDone(form.isDone());
        return todoRepository.save(todoItem);
    }
}
