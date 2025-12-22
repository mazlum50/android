package de.mazlum.erstapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.mazlum.erstapp.logic.VmpCategory;
import de.mazlum.erstapp.logic.WorkoutLogic;
import de.mazlum.erstapp.model.WorkoutPlan;

public class WorkoutActivity extends AppCompatActivity {
    private TextView titleText;
    private TextView frequencyText;
    private TextView focusText;
    private TextView notesText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        titleText = findViewById(R.id.titleText);
        frequencyText = findViewById(R.id.frequencyText);
        focusText = findViewById(R.id.focusText);
        notesText = findViewById(R.id.notesText);
        WorkoutPlan workoutPlan = (WorkoutPlan) getIntent().getSerializableExtra("WORKOUT_PLAN");
        if (workoutPlan != null) {
           titleText.setText(workoutPlan.getTitle());
           frequencyText.setText(workoutPlan.getFrequency());
           focusText.setText(workoutPlan.getFocus());
           notesText.setText(workoutPlan.getNotes());
        }
    }

}