package com.example.jpaassignment.Repository;

import com.example.jpaassignment.entity.Ingredient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IngredientRepositoryTest {
    @Autowired
    private IngredientRepository testObject;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @BeforeAll
    void setUp() {
        ingredient1 = new Ingredient("kanel");
        ingredient2 = new Ingredient("banan");
        testObject.save(ingredient1);
        testObject.save(ingredient2);
    }

    @Test
    public void shouldReturnOneObject() {
        Ingredient actual = testObject.findByIngredientName("Kanel").orElse(null);
        Assertions.assertEquals(ingredient1, actual);
    }
    @Test
    public void shouldReturn2Objects(){
        Set<Ingredient> ingredients = testObject.findByIngredientNameContainingIgnoreCase("an");
        System.out.println(ingredients);
        Assertions.assertEquals(2,ingredients.size());
    }
}
