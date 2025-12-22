package de.mazlum.erstapp.model;

import java.io.Serializable;

public class WorkoutPlan implements Serializable {
    private String title;
    private String frequency;
    private String focus;
    private String notes;

    public WorkoutPlan(String title, String frequency, String focus, String notes) {
        this.title = title;
        this.frequency = frequency;
        this.focus = focus;
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getFocus() {
        return focus;
    }

    public String getNotes() {
        return notes;
    }
}
