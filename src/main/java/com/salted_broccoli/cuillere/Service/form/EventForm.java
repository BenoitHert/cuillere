package com.salted_broccoli.cuillere.Service.form;

import com.salted_broccoli.cuillere.Model.Calendar.Level;
import com.salted_broccoli.cuillere.Model.Calendar.Type;
import lombok.Data;

import java.util.Date;

@Data
public class EventForm {
    private Date date;
    private String title;
    private Level level;
    private Type type;
    private Integer duration;
}
