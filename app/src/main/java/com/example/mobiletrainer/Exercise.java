package com.example.mobiletrainer;

/**
 * Created by curtd on 3/21/2018.
 */

public class Exercise {
    private String name, description, category;
    private int sets, reps;

    public Exercise (String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Exercise(String name, String description, String category, int sets, int reps) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.sets = sets;
        this.reps = reps;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCategory() {
        return this.category;
    }

    public int getSets () {
        return this.sets;
    }

    public int getReps() {
        return this.reps;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
