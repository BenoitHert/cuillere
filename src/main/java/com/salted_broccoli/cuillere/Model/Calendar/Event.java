package com.salted_broccoli.cuillere.Model.Calendar;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    private Date date;
    private String title;
    private Level level;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Type type;
    private String duration;
}
