package com.salted_broccoli.cuillere.Repository;

import com.salted_broccoli.cuillere.Model.Calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository <Calendar, Long> {
}
