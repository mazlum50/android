package de.mazlum.erstapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import de.mazlum.erstapp.logic.VmpCalculator;
import de.mazlum.erstapp.logic.WorkoutLogic;
import de.mazlum.erstapp.model.UserGoal;
import de.mazlum.erstapp.model.VmpCategory;
import de.mazlum.erstapp.model.WorkoutPlan;

public class MainActivity extends AppCompatActivity {

    // UI elements
    private EditText weightInput;
    private EditText heightInput;
    private RadioGroup goalRadioGroup;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1️⃣ ربط عناصر الواجهة
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        goalRadioGroup = findViewById(R.id.goalRadioGroup);
        startButton = findViewById(R.id.startButton);

        // 2️⃣ زر Start
        startButton.setOnClickListener(v -> onStartClicked());
    }

    /**
     * يتم استدعاؤها عند ضغط زر Start
     * تجمع المدخلات، تحسب VMP، وتفتح WorkoutActivity
     */
    private void onStartClicked() {

        // --- Validation ---
        if (weightInput.getText().toString().isEmpty()
                || heightInput.getText().toString().isEmpty()) {

            Toast.makeText(this,
                    "Bitte Gewicht und Größe eingeben",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int checkedId = goalRadioGroup.getCheckedRadioButtonId();
        if (checkedId == -1) {
            Toast.makeText(this,
                    "Bitte Ziel auswählen",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // --- Parse input ---
        float weight = Float.parseFloat(weightInput.getText().toString());
        float height = Float.parseFloat(heightInput.getText().toString());

        // --- Translate UI choice to Enum ---
        UserGoal userGoal;
        if (checkedId == R.id.radioMuscle) {
            userGoal = UserGoal.MUSCLE;
        } else {
            userGoal = UserGoal.FAT_LOSS;
        }

        // --- Calculate VMP ---
        VmpCategory vmpCategory = VmpCalculator.calculate(
                userGoal,
                weight,
                height
        );

        // --- Build workout plan ---
        WorkoutPlan plan = WorkoutLogic.getWorkoutPlan(vmpCategory);

        // --- Open WorkoutActivity ---
        Intent intent = new Intent(MainActivity.this, WorkoutActivity.class);
        intent.putExtra("WORKOUT_PLAN", plan);
        startActivity(intent);
    }
}