package de.mazlum.erstapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.mazlum.erstapp.model.WorkoutPlan;
import de.mazlum.erstapp.ui.adapter.WorkoutDayAdapter;

public class WorkoutActivity extends AppCompatActivity {
    private TextView titleText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        titleText = findViewById(R.id.titleText);

        RecyclerView recyclerView = findViewById(R.id.daysRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        WorkoutPlan plan =
                (WorkoutPlan) getIntent().getSerializableExtra("WORKOUT_PLAN");
        Log.d("Debug","Plan " + plan);
        if (plan != null) {
            titleText.setText(plan.getTitle());

            WorkoutDayAdapter adapter =
                    new WorkoutDayAdapter(plan.getDays());

            recyclerView.setAdapter(adapter);
        }
    }

}