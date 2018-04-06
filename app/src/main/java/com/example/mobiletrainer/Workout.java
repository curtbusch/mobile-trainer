package com.example.mobiletrainer;

/**
 * Created by curtd on 3/25/2018.
 */

public class Workout {
    private int id;
    private String name;
    private int isComplete;

    public Workout(int id, String name, int isComplete) {
        this.id = id;
        this.name = name;
        this.isComplete = isComplete;
    }

    public int getId() { return  this.id; }

    public String getName() {
        return this.name;
    }

    public int getIsComplete() {
        return this.isComplete;
    }
}
