package de.mazlum.erstapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import de.mazlum.erstapp.data.entity.User;
import de.mazlum.erstapp.data.database.AppDatabase;
import de.mazlum.erstapp.logic.VmpCalculator;
import de.mazlum.erstapp.logic.VmpCategory;
import de.mazlum.erstapp.logic.WorkoutLogic;
import de.mazlum.erstapp.model.WorkoutPlan;

public class MainActivity extends AppCompatActivity {

    private ExecutorService executorService;
    private AppDatabase database;
    private Handler mainHandler= new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        executorService = Executors.newSingleThreadExecutor();
        database = AppDatabase.getInstance(this);
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> saveUser());

    }

    private void saveUser() {
        EditText weightInput = findViewById(R.id.weightInput);
        EditText heightInput = findViewById(R.id.heightInput);
        int weight = Integer.parseInt(weightInput.getText().toString());
        int height = Integer.parseInt(heightInput.getText().toString());
        String goal = "bulk";
        User user = new User(goal ,height , weight);
        executorService.execute(()-> {
            database.userDao().insertUser(user);
        });
        executorService.execute(()->{
            database.userDao().insertUser(user);
            loadUser();
        });
    }
    private void loadUser() {
        executorService.execute(()->{
            User user =database.userDao().getUser();
            mainHandler.post(()->{
                if (user != null){
                    VmpCategory vmp = VmpCalculator.getVmpCategory(user.getWeight() ,user.getHeight());
                    WorkoutPlan workoutPlan = WorkoutLogic.getWorkoutPlan(vmp);
                  Intent intent = new Intent( MainActivity.this ,WorkoutActivity.class);
                    intent.putExtra("WORKOUT_PLAN" ,workoutPlan);
                  intent.putExtra("VMP_CATEGORY" ,vmp.name());
                  startActivity(intent);
                }
            });

        });
    }
}