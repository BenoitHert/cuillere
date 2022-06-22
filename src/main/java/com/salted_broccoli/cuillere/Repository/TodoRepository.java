package com.salted_broccoli.cuillere.Repository;

import com.salted_broccoli.cuillere.Model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository <TodoItem, Long> {
}
