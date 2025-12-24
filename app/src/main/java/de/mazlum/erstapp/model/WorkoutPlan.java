package de.mazlum.erstapp.model;

import java.io.Serializable;
import java.util.List;

public class WorkoutPlan implements Serializable {
    private String title;
    private List<WorkoutDay> days;

    public WorkoutPlan(String title, List<WorkoutDay> days) {
        this.title = title;
        this.days = days;
    }

    public String getTitle() {
        return title;
    }

    public List<WorkoutDay> getDays() {
        return days;
    }
}
