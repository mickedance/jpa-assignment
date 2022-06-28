package com.example.jpaassignment.Repository;

import com.example.jpaassignment.entity.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    Optional<Recipe> findByRecipeName(String recipeName);

    @Query("Select r from Recipe r, RecipeIngredient ri, Ingredient i where i.ingredientName =:ingredientName" +
            " and ri.ingredient.id = i.id and" +
            " ri.recipe.id = r.id")
    Set<Recipe> findByIngredientName(@Param("ingredientName") String ingredientName);

    @Query("select r from Recipe r join fetch r.categories category where category.categoryName in :categoryName")
    Set<Recipe> findRecipesByCategoryName(@Param("categoryName") String categoryName);

    @Query("select r from Recipe r join fetch r.categories category where category.categoryName in :categoryNames")
    Set<Recipe> findRecipesByCategoryNames(@Param("categoryNames") List<String> categoryNames);
}
