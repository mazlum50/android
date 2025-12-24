package de.mazlum.erstapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.mazlum.erstapp.R;
import de.mazlum.erstapp.model.Exercise;
import de.mazlum.erstapp.model.WorkoutDay;

public class WorkoutDayAdapter extends RecyclerView.Adapter<WorkoutDayAdapter.DayViewHolder> {
    private List <WorkoutDay> days;

    public WorkoutDayAdapter(List<WorkoutDay> days) {
        this.days = days;
    }
    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_workout_day, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        WorkoutDay day = days.get(position);

        holder.dayTitleText.setText(day.getDayName());

        // بناء نص التمارين
        StringBuilder exercisesBuilder = new StringBuilder();
        for (Exercise exercise : day.getExercises()) {
            exercisesBuilder.append("• ")
                    .append(exercise.getName())
                    .append(" ")
                    .append(exercise.getSets())
                    .append("x")
                    .append(exercise.getReps())
                    .append("\n");
        }

        holder.exercisesText.setText(exercisesBuilder.toString());
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {

        TextView dayTitleText;
        TextView exercisesText;

        DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTitleText = itemView.findViewById(R.id.dayTitleText);
            exercisesText = itemView.findViewById(R.id.exercisesText);
        }
    }
}
