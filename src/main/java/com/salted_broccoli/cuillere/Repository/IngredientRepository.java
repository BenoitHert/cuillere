package com.salted_broccoli.cuillere.Repository;

import com.salted_broccoli.cuillere.Model.Menu.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findIngredientById(Long id);
}
