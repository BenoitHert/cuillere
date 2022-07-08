package com.salted_broccoli.cuillere.Repository;

import com.salted_broccoli.cuillere.Model.Todolist.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository <TodoItem, Long> {
    List<TodoItem> findItemsByUserId(Long id);
    TodoItem findItemById(Long id);

}
