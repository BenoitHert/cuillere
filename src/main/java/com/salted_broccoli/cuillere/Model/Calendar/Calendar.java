package com.salted_broccoli.cuillere.Model.Calendar;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Calendar {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    @OneToMany
    private List<Event> eventList;
}
