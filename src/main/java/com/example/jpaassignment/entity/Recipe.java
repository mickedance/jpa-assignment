package com.example.jpaassignment.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private int id;
    @Column(updatable = false, nullable = false, unique = true)
    private String recipeName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructions_id")
    private RecipeInstructions instructions;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "recipe", orphanRemoval = true)
    private Set<RecipeIngredient> recipeIngredientSet;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name="recipe_in_categories", inverseJoinColumns = @JoinColumn(name = "recipe_category_id"),
            joinColumns = @JoinColumn(name = "recipe_id") )
    private Set<RecipeCategory> categories;

    public Recipe() {
    }

    public Recipe(String recipeName, RecipeInstructions instructions) {
        this.recipeName = recipeName;
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public RecipeInstructions getInstructions() {
        return instructions;
    }

    public void setInstructions(RecipeInstructions instructions) {
        this.instructions = instructions;
    }

    public Set<RecipeIngredient> getRecipeIngredientSet() {
        return recipeIngredientSet;
    }

    public void setRecipeIngredientSet(Set<RecipeIngredient> recipeIngredientSet) {
        this.recipeIngredientSet = recipeIngredientSet;
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        if(recipeIngredientSet == null) recipeIngredientSet = new HashSet<>();
        if(!recipeIngredientSet.contains(recipeIngredient)) recipeIngredientSet.add(recipeIngredient);
        recipeIngredient.setRecipe(this);
    }
    public void removeRecipeIngredient(RecipeIngredient _recipeIngredient){
        if(recipeIngredientSet == null) recipeIngredientSet = new HashSet<>();
        _recipeIngredient.setRecipe(null);
        if(recipeIngredientSet.contains(_recipeIngredient)) recipeIngredientSet.remove(_recipeIngredient);
    }
    public void addRecipeCategory(RecipeCategory category){
        if(categories==null) categories = new HashSet<>();
        if(!categories.contains(category)) categories.add(category);
    }
    public void removeRecipeCategory(RecipeCategory category){
        if(categories==null) categories = new HashSet<>();
        if(categories.contains(category)) categories.remove(category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id && recipeName.equals(recipe.recipeName) && instructions.equals(recipe.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeName, instructions);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", instructions=" + instructions +
                ", recipeIngredientSet=" + recipeIngredientSet +
                ", categories=" + categories +
                '}';
    }
}
