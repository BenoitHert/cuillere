package com.salted_broccoli.cuillere.Model.Calendar;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Type {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO, generator = "EVENTTYPE_SEQ")
    private Long id;
    private String type;
}
