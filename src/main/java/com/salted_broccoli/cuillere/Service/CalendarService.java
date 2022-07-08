package com.salted_broccoli.cuillere.Service;

import com.salted_broccoli.cuillere.Model.Calendar.Calendar;
import com.salted_broccoli.cuillere.Model.Calendar.Event;
import com.salted_broccoli.cuillere.Model.Calendar.Type;
import com.salted_broccoli.cuillere.Model.User;
import com.salted_broccoli.cuillere.Repository.CalendarRepository;
import com.salted_broccoli.cuillere.Repository.EventRepository;
import com.salted_broccoli.cuillere.Repository.TypeRepository;
import com.salted_broccoli.cuillere.Repository.UserRepository;
import com.salted_broccoli.cuillere.Service.form.EventForm;
import com.salted_broccoli.cuillere.Service.form.TypeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service("Calendar Service")
public class CalendarService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CalendarRepository calendarRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    TypeRepository typeRepository;


//    TODO Couverture de tests

    public User findUser() {
        //Spring Security-side identifier; tied to the user's session => user's email
        String connectedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        //Fetch according to the email
        return userRepository.findUserByEmail(connectedUserEmail);
    }

    public Event createEvent(EventForm form) {
        if (isValidDate(form.getDate())) {
            if (form.getDuration() < 0) {
                if (!form.getTitle().isEmpty()) {
                    Event event = new Event();
                    event.setDuration(form.getDuration());
                    event.setDate(form.getDate());
                    event.setLevel(form.getLevel());
                    event.setTitle(form.getTitle());
                    event.setType(form.getType());
                    return eventRepository.save(event);
                } else {
                    throw new RuntimeException("Entrez un titre");
                }
            } else {
                throw new ArithmeticException("La durée d'un évennement ne peut pas être négative");
            }
        } else {
            throw new RuntimeException("Entrez une date valide, vous ne pouvez pas entrer une date passée");
        }
    }

//    TODO deleteEvent
    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    public Calendar addEventToCalendar(EventForm form) {
        User user = findUser();
        Calendar calendar = user.getCalendar();
        Event event = createEvent(form);
        calendar.getEventList().add(event);
        return calendarRepository.save(calendar);
    }

    public Calendar removeEventFromCalendar(Long id) {
        User user = findUser();
        Calendar calendar = user.getCalendar();
        Event event = eventRepository.findEventById(id);
        calendar.getEventList().remove(event);
        deleteEvent(event);
        return calendarRepository.save(calendar);

    }

    public Type createType(TypeForm form) {
        Type type = new Type();
        type.setType(form.getType());
        return typeRepository.save(type);
    }

    public void deleteType(Long id) {
        Type type = typeRepository.findTypeById(id);
        typeRepository.delete(type);
    }

    public boolean isValidDate(Date date) {
//        ZoneId z = ZoneId.of( "America/Montreal" );
//        TODO Timezone inclusion
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate today = LocalDate.now();
        return date.after(Date.from(today.atStartOfDay(defaultZoneId).toInstant()));

    }

}
