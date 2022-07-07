package com.salted_broccoli.cuillere.Model.Todolist;

import com.salted_broccoli.cuillere.Model.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TodoItem {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String title;
    private boolean done;




}
