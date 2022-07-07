package com.salted_broccoli.cuillere.Service;

import com.salted_broccoli.cuillere.Model.Todolist.TodoItem;
import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Repository.TodoRepository;
import com.salted_broccoli.cuillere.Repository.UserRepository;
import com.salted_broccoli.cuillere.Service.form.ItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ToDo Service")
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    public User findUser() {
        return userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public TodoItem itemSave(ItemForm form){
        User user = findUser();
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(form.getTitle());
        todoItem.setDone(false);
        user.getTodolist().add(todoItem);
        return todoRepository.save(todoItem);
    }

    public TodoItem itemUpdate(Long id, Boolean b){
        TodoItem item = todoRepository.findById(id).get();
        item.setDone(b);
        return todoRepository.save(item);

    }

    public List<TodoItem> findItems(){
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User connectedUser = userRepository.findUserByEmail(springUser.getUsername());
        return connectedUser.getTodolist();
    }
}
