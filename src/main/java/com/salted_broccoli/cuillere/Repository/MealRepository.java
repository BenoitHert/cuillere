package com.salted_broccoli.cuillere.Repository;

import com.salted_broccoli.cuillere.Model.Menu.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository <Meal, Long> {
}
