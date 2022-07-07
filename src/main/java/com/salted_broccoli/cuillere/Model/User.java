package com.salted_broccoli.cuillere.Model;

import com.salted_broccoli.cuillere.Model.Calendar.Calendar;
import com.salted_broccoli.cuillere.Model.Menu.Meal;
import com.salted_broccoli.cuillere.Model.Menu.Recipe;
import com.salted_broccoli.cuillere.Model.Shoplist.ShopItem;
import com.salted_broccoli.cuillere.Model.Todolist.TodoItem;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id

    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TodoItem> todolist;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Meal> meals;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Calendar calendar;
    @OneToMany(cascade = CascadeType.ALL)
    private List <ShopItem> shoplist;


}
