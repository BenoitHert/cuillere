package com.salted_broccoli.cuillere.Repository;

import com.salted_broccoli.cuillere.Model.Menu.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findRecipeByid(Long id);
}
