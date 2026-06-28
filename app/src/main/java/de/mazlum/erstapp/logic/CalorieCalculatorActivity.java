package de.mazlum.erstapp.logic;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.mazlum.erstapp.R;
import de.mazlum.erstapp.data.dao.UserDao;
import de.mazlum.erstapp.data.database.AppDatabase;
import de.mazlum.erstapp.data.entity.User;
import de.mazlum.erstapp.model.UserGoal;

public class CalorieCalculatorActivity extends AppCompatActivity {

    // UI-Komponenten
    private RadioGroup genderRadioGroup;
    private RadioGroup activityRadioGroup;
    private Button calculateButton;
    private TextView resultTextView;
    private Button backButton;

    // Speicher für geladene Benutzerdaten
    private User currentUser;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);

        initViews();
        loadUserData();

        calculateButton.setOnClickListener(v -> processCalorieCalculation());
        backButton.setOnClickListener(v -> finish());
    }

    private void initViews() {
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        activityRadioGroup = findViewById(R.id.activityRadioGroup);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        backButton = findViewById(R.id.backToWorkoutButton);
    }

    // Lädt die bereits gespeicherten Benutzerdaten aus der Datenbank
    private void loadUserData() {
        executor.execute(() -> {
            try {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                UserDao userDao = db.userDao();
                currentUser = userDao.getUser();

                if (currentUser == null) {
                    runOnUiThread(() -> Toast.makeText(CalorieCalculatorActivity.this,
                            "Keine Benutzerdaten gefunden!", Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Verarbeitet die Berechnung nach Auswahl von Geschlecht und Aktivitätsniveau
    private void processCalorieCalculation() {
        // 1️⃣ Validierung: Überprüfen ob Daten geladen sind (Verhindert NullPointerException)
        if (currentUser == null) {
            Toast.makeText(this, "Benutzerdaten werden noch geladen...", Toast.LENGTH_SHORT).show();
            return;
        }

        // 2️⃣ Validierung: Geschlecht ausgewählt?
        int checkedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        if (checkedGenderId == -1) {
            Toast.makeText(this, "Bitte wählen Sie Ihr Geschlecht aus", Toast.LENGTH_SHORT).show();
            return;
        }

        // 3️⃣ Validierung: Aktivitätsniveau ausgewählt?
        int checkedActivityId = activityRadioGroup.getCheckedRadioButtonId();
        if (checkedActivityId == -1) {
            Toast.makeText(this, "Bitte wählen Sie Ihr Aktivitätsniveau aus", Toast.LENGTH_SHORT).show();
            return;
        }

        // Bestimmung des PAL-Faktors (Aktivitätsfaktor)
        double palFaktor = 1.2;
        if (checkedActivityId == R.id.radioLowActivity) {
            palFaktor = 1.375;
        } else if (checkedActivityId == R.id.radioMediumActivity) {
            palFaktor = 1.55;
        } else if (checkedActivityId == R.id.radioHighActivity) {
            palFaktor = 1.725;
        }

        // 4️⃣ Berechnung des Grundumsatzes (Mifflin-St. Jeor Formel)
        // Basis: (10 * Gewicht) + (6.25 * Größe) - (5 * 37) -> Alter fixiert auf 37
        double baseBmr = (10 * currentUser.getWeight()) + (6.25 * currentUser.getHeight()) - 185;
        double bmr = baseBmr;

        // Anpassung je nach Geschlecht (+5 für Männer, -161 für Frauen)
        if (checkedGenderId == R.id.radioMale) {
            bmr += 5;
        } else if (checkedGenderId == R.id.radioFemale) {
            bmr -= 161;
        }

        // 5️⃣ Gesamtumsatz (Erhaltungskalorien)
        double tdee = bmr * palFaktor;

        // 6️⃣ Anpassung basierend auf dem Ziel
        double zielKalorien = tdee;
        String zielText = "Ziel: Gewicht halten";

        if (currentUser.getGoal() == UserGoal.MUSCLE) {
            zielKalorien += 300;
            zielText = "Ziel: Muskelaufbau (+300 kcal Überschuss)";
        } else if (currentUser.getGoal() == UserGoal.FAT_LOSS) {
            zielKalorien -= 500;
            zielText = "Ziel: Fettabbau (-500 kcal Defizit)";
        }

        // Ergebnis auf dem Bildschirm anzeigen
        @SuppressLint("DefaultLocale") String resultMessage = String.format(
                "Ihr Grundumsatz: %.0f kcal\n" +
                        "Erhaltungskalorien: %.0f kcal\n\n" +
                        "Empfohlene Tageszufuhr:\n%.0f kcal\n\n%s",
                bmr, tdee, zielKalorien, zielText
        );

        resultTextView.setText(resultMessage);
    }
}