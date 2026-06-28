package de.mazlum.erstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.mazlum.erstapp.logic.CalorieCalculatorActivity;
import de.mazlum.erstapp.model.WorkoutPlan;
import de.mazlum.erstapp.ui.adapter.WorkoutDayAdapter;

public class WorkoutActivity extends AppCompatActivity {

    // UI-Komponenten
    private TextView titleText;
    private Button backButton;
    private Button calculateCaloriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        // Views initialisieren
        titleText = findViewById(R.id.titleText);
        backButton = findViewById(R.id.backButton);
        calculateCaloriesButton = findViewById(R.id.calculateCaloriesButton);

        // RecyclerView für die Trainingstage einrichten
        RecyclerView recyclerView = findViewById(R.id.daysRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Trainingsplan aus dem Intent abrufen
        WorkoutPlan plan = (WorkoutPlan) getIntent().getSerializableExtra("WORKOUT_PLAN");
        Log.d("Debug", "Plan " + plan);

        if (plan != null) {
            titleText.setText(plan.getTitle());

            WorkoutDayAdapter adapter = new WorkoutDayAdapter(plan.getDays());
            recyclerView.setAdapter(adapter);
        }

        // Klick-Listener für die Buttons einrichten
        setupClickListeners();
    }

    // ================= Interaktionen & Navigation =================

    private void setupClickListeners() {
        // 1️⃣ Zurück-Button: Schließt die aktuelle Activity und kehrt zum vorherigen Screen zurück
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // 2️⃣ Kalorienrechner-Button: Navigiert zur Aktivität für die Kalorienberechnung
        if (calculateCaloriesButton != null) {
            calculateCaloriesButton.setOnClickListener(v -> {
                Intent intent = new Intent(WorkoutActivity.this, CalorieCalculatorActivity.class);
                startActivity(intent);
            });
        }
    }
}