package com.example.jpaassignment.Repository;

import com.example.jpaassignment.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.Set;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    Optional<Ingredient> findByIngredientName(String ingredientName);
    Set<Ingredient> findByIngredientNameContainingIgnoreCase(String ingredientName);
}
