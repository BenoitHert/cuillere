package com.salted_broccoli.cuillere.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id

    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;

}
