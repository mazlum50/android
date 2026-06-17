package de.mazlum.erstapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.mazlum.erstapp.data.dao.UserDao;
import de.mazlum.erstapp.data.database.AppDatabase;
import de.mazlum.erstapp.data.entity.User;
import de.mazlum.erstapp.logic.VmpCalculator;
import de.mazlum.erstapp.logic.WorkoutLogic;
import de.mazlum.erstapp.model.UserGoal;
import de.mazlum.erstapp.model.VmpCategory;
import de.mazlum.erstapp.model.WorkoutPlan;

public class MainActivity extends AppCompatActivity {

    // UI
    private EditText weightInput;
    private EditText heightInput;
    private RadioGroup goalRadioGroup;
    private RadioGroup daysRadioGroup;
    private Button startButton;

    // Thread executor (واحد يكفي الآن)
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        loadUser();

        startButton.setOnClickListener(v -> onStartClicked());
    }

    // ================= UI =================

    private void initViews() {
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        goalRadioGroup = findViewById(R.id.goalRadioGroup);
        startButton = findViewById(R.id.startButton);
        daysRadioGroup = findViewById(R.id.daysRadioGroup);
    }

    // ================= FLOW =================

    private void onStartClicked() {

        // Validation
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
        int daysCheckedId = daysRadioGroup.getCheckedRadioButtonId();
        if (daysCheckedId == -1){
            Toast.makeText(this ,"Bitte Trainingstage auswählen ",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse input
        int weight = Integer.parseInt((weightInput.getText().toString()));
        int height = Integer.parseInt(heightInput.getText().toString());

        UserGoal goal;
        if (checkedId == R.id.radioMuscle) {
            goal = UserGoal.MUSCLE;
        } else {
            goal = UserGoal.FAT_LOSS;
        }
        int trainingDays;
        if (daysCheckedId == R.id.radio2Days){
            trainingDays = 2;
        }
        else if (daysCheckedId == R.id.radio3Days) {
            trainingDays = 3;
        }
        else if (daysCheckedId == R.id.radio4Days) {
            trainingDays = 4;
        }
        else if (daysCheckedId == R.id.radio5Days) {
            trainingDays = 5;
        }
        else if (daysCheckedId == R.id.radio6Days) {
            trainingDays = 6;
        }else {
            trainingDays = 3;
        }

        // 1️⃣ حفظ المستخدم
        saveUser(weight, height, goal, trainingDays);

        // 2️⃣ حساب VMP
        VmpCategory vmpCategory = VmpCalculator.calculate(goal, weight, height);

        // 3️⃣ بناء خطة التدريب
        WorkoutPlan plan = WorkoutLogic.getWorkoutPlan(vmpCategory ,trainingDays);

        if (plan == null) {
            Toast.makeText(this,
                    "Kein Trainingsplan gefunden",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // 4️⃣ الانتقال لصفحة الخطة
        Intent intent = new Intent(this, WorkoutActivity.class);
        intent.putExtra("WORKOUT_PLAN", plan);
        startActivity(intent);
    }

    // ================= ROOM =================

    private void saveUser(int weight, int height, UserGoal goal, int trainingDays) {

        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            UserDao userDao = db.userDao();

            User user = new User(goal ,weight ,height);
            user.setTrainingDays(trainingDays);
            userDao.insert(user);
        });
    }

    private void loadUser() {

        executor.execute(() -> {
            try {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                UserDao userDao = db.userDao();

                User user = userDao.getUser();
                if (user == null) return;

                runOnUiThread(() -> {
                    if (weightInput != null) {
                        weightInput.setText(String.valueOf(user.getWeight()));
                    }
                    if (heightInput != null) {
                        heightInput.setText(String.valueOf(user.getHeight()));

                    }
                    if (goalRadioGroup != null) {
                        if (user.getGoal() == UserGoal.MUSCLE) {
                            goalRadioGroup.check(R.id.radioMuscle);
                        } else if (user.getGoal() != null) {
                            goalRadioGroup.check(R.id.radioFatLoss);
                        }
                    }
                    if (daysRadioGroup != null) {
                        switch (user.getTrainingDays()) {
                            case 2:
                                daysRadioGroup.check(R.id.radio2Days);
                                break;
                            case 3:
                                daysRadioGroup.check(R.id.radio3Days);
                                break;
                            case 4:
                                daysRadioGroup.check(R.id.radio4Days);
                                break;
                            case 5:
                                daysRadioGroup.check(R.id.radio5Days);
                                break;
                            case 6:
                                daysRadioGroup.check(R.id.radio6Days);
                                break;
                        }
                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}