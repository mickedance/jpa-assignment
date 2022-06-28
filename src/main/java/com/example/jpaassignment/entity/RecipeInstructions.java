package com.example.jpaassignment.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RecipeInstructions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private int id;
    @Column(updatable = false, nullable = false)
    private String instructions;

    public RecipeInstructions() {
    }

    public RecipeInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeInstructions that = (RecipeInstructions) o;
        return id == that.id && instructions.equals(that.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instructions);
    }

    @Override
    public String toString() {
        return "RecipeInstructions{" +
                "id=" + id +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
