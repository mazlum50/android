package de.mazlum.erstapp.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.mazlum.erstapp.model.Exercise;
import de.mazlum.erstapp.model.VmpCategory;
import de.mazlum.erstapp.model.WorkoutDay;
import de.mazlum.erstapp.model.WorkoutPlan;

public class WorkoutLogic {
    public static WorkoutPlan getWorkoutPlan(VmpCategory category) {

        switch (category) {

            case MUSCLE_BUILD:
                return buildMusclePlan();

            case RECOMP:
                return buildRecompPlan();

            case FAT_LOSS:
                return buildFatLossPlan();

            default:
                throw new IllegalStateException("Unknown VMP category");
        }
    }

    private static WorkoutPlan buildMusclePlan() {

        List<WorkoutDay> days = new ArrayList<>();

        days.add(new WorkoutDay(
                "Tag 1 – Ganzkörper",
                Arrays.asList(
                        new Exercise("Kniebeugen", 4, 8),
                        new Exercise("Bankdrücken", 4, 8),
                        new Exercise("Rudern", 3, 10)
                )
        ));

        days.add(new WorkoutDay(
                "Tag 2 – Ganzkörper",
                Arrays.asList(
                        new Exercise("Kreuzheben", 3, 5),
                        new Exercise("Schulterdrücken", 4, 8),
                        new Exercise("Klimmzüge", 3, 8)
                )
        ));

        return new WorkoutPlan("Muskelaufbau", days);
    }

    private static WorkoutPlan buildRecompPlan() {

        List<WorkoutDay> days = new ArrayList<>();

        days.add(new WorkoutDay(
                "Tag 1 – Oberkörper",
                Arrays.asList(
                        new Exercise("Bankdrücken", 4, 8),
                        new Exercise("Latzug", 3, 10)
                )
        ));

        days.add(new WorkoutDay(
                "Tag 2 – Unterkörper",
                Arrays.asList(
                        new Exercise("Beinpresse", 4, 10),
                        new Exercise("Beinbeuger", 3, 12)
                )
        ));

        return new WorkoutPlan("Rekomposition", days);
    }

    private static WorkoutPlan buildFatLossPlan() {

        List<WorkoutDay> days = new ArrayList<>();

        days.add(new WorkoutDay(
                "Tag 1 – Zirkel",
                Arrays.asList(
                        new Exercise("Burpees", 3, 15),
                        new Exercise("Kettlebell Squats", 3, 12)
                )
        ));

        return new WorkoutPlan("Fettabbau", days);
    }
}
