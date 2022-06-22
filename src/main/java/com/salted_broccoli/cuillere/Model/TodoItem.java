package com.salted_broccoli.cuillere.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TodoItem {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ")
    private Long id;
    private String title;
    private boolean done;
    @ManyToOne
    private User user;


}
