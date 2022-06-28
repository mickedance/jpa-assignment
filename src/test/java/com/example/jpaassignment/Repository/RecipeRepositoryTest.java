package com.example.jpaassignment.Repository;

import com.example.jpaassignment.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

@Rollback(value = true)

public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    TestEntityManager em;

    private static Recipe recipe1;
    private static Recipe recipe2;
    private static Recipe recipe3;

   @BeforeEach
    public  void setUp(){
       recipe1 = new Recipe("Pannkaka",new RecipeInstructions("shake it"));
       Ingredient cream = em.persistAndFlush(new Ingredient("cream"));
       RecipeIngredient recipeIngredient = new RecipeIngredient(cream, Measurement.DL, 11.0);
       RecipeIngredient savedRecIng = em.persistAndFlush(recipeIngredient);
       RecipeCategory category = em.persistAndFlush(new RecipeCategory("softa skakor"));
       recipe1.addRecipeIngredient(savedRecIng);
       recipe1.addRecipeCategory(category);
       recipe1 = recipeRepository.save(recipe1);

       recipe2 = new Recipe("omelette",new RecipeInstructions("shake it"));
       RecipeIngredient recipeIngredient2 = new RecipeIngredient(cream, Measurement.DL, 11.0);
       RecipeIngredient savedRecIng2 = em.persistAndFlush(recipeIngredient2);
       recipe2.addRecipeIngredient(savedRecIng2);
       recipe2.addRecipeCategory(category);
       recipe2 = recipeRepository.save(recipe2);

       recipe3 = new Recipe("stinas mat",new RecipeInstructions("shake it"));
       RecipeIngredient recipeIngredient3 = new RecipeIngredient(new Ingredient("ost"), Measurement.DL, 11.0);
       RecipeIngredient savedRecIng3 = em.persistAndFlush(recipeIngredient3);
       recipe3.addRecipeIngredient(savedRecIng3);
       RecipeCategory category2 = em.persistAndFlush(new RecipeCategory("saft"));
       recipe3.addRecipeCategory(category2);
       recipe3 = recipeRepository.save(recipe3);
    }
    @Test
    public void test_should_return_2_objects() {

        Set<Recipe> recipeSet = recipeRepository.findByIngredientName("cream");
        for(Recipe r: recipeSet){
            System.out.println(r.getRecipeName());
        }
        Assertions.assertEquals(2, recipeSet.size());
    }
    @Test
    public void test_should_return_1_objects() {
        Set<Recipe> recipeSet = recipeRepository.findRecipesByCategoryName("saft");
        for(Recipe r: recipeSet){
            System.out.println(r.getRecipeName());
        }
        Assertions.assertEquals(1, recipeSet.size());

    }
    @Test
    public void test_successfully3() {
       List<String> categories = new ArrayList<>();
        categories.add("saft");
        Set<Recipe> recipeSet = recipeRepository.findRecipesByCategoryNames(categories);
        for(Recipe r: recipeSet){
            System.out.println(r.getRecipeName());
        }
        Assertions.assertEquals(1, recipeSet.size());

    }
}
