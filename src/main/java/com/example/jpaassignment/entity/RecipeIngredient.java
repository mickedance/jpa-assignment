package com.example.jpaassignment.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Ingredient ingredient;
    @Column(nullable = false, updatable = false)
    private double amount;
    @Column(nullable = false, updatable = false)
    private Measurement measurement;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredient, Measurement measurement) {
        this.ingredient = ingredient;
        this.measurement = measurement;
    }

    public RecipeIngredient(Ingredient ingredient,  Measurement measurement, double amount ) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.measurement = measurement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return id == that.id && ingredient.equals(that.ingredient) && measurement == that.measurement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredient, measurement);
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "id=" + id +
                ", ingredient=" + ingredient +
                ", measurement=" + measurement +
                '}';
    }
}
