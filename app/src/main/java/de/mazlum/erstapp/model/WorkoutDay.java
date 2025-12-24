package de.mazlum.erstapp.model;

import java.io.Serializable;
import java.util.List;

public class WorkoutDay implements Serializable {
    public String dayName;
    private List<Exercise> exercises;

    public WorkoutDay(String dayName, List<Exercise> exercises) {
        this.dayName = dayName;
        this.exercises = exercises;
    }

    public String getDayName() {
        return dayName;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
