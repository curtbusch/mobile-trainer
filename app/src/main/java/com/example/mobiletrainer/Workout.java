package com.example.mobiletrainer;

/**
 * Created by curtd on 3/25/2018.
 */

public class Workout {
    private String name;
    private int isComplete;

    public Workout(String name, int isComplete) {
        this.name = name;
        this.isComplete = isComplete;
    }

    public String getName() {
        return this.name;
    }

    public int getIsComplete() {
        return this.isComplete;
    }
}
