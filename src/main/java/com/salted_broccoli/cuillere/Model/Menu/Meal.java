package com.salted_broccoli.cuillere.Model.Menu;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Meal {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Recipe appetizer;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Recipe maindish;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Recipe desert;
}
