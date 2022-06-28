package com.example.jpaassignment.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private int id;
    @Column(nullable = false, updatable = false, unique = true)
    private String categoryName;
    @ManyToMany
    @JoinTable(name="recipe_in_categories", joinColumns = @JoinColumn(name = "recipe_category_id"),
    inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipeSet;


    public RecipeCategory() {
    }

    public RecipeCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Recipe> getRecipeSet() {
        return recipeSet;
    }

    public void setRecipeSet(Set<Recipe> recipeSet) {
        this.recipeSet = recipeSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCategory category = (RecipeCategory) o;
        return id == category.id && categoryName.equals(category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }

    @Override
    public String toString() {
        return "RecipeCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
