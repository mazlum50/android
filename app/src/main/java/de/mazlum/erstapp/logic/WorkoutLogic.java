package de.mazlum.erstapp.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.mazlum.erstapp.model.Exercise;
import de.mazlum.erstapp.model.VmpCategory;
import de.mazlum.erstapp.model.WorkoutDay;
import de.mazlum.erstapp.model.WorkoutPlan;

public class WorkoutLogic {

    // قمنا بإضافة باراميتر trainingDays هنا
    public static WorkoutPlan getWorkoutPlan(VmpCategory category, int trainingDays) {

        switch (category) {
            case MUSCLE_BUILD:
                return buildMusclePlan(trainingDays);

            case RECOMP:
                return buildRecompPlan(trainingDays);

            case FAT_LOSS:
                return buildFatLossPlan(trainingDays);

            default:
                throw new IllegalStateException("Unknown VMP category");
        }
    }

    private static WorkoutPlan buildMusclePlan(int daysCount) {
        List<WorkoutDay> days = new ArrayList<>();

        if (daysCount == 2) {
            // يومين فقط: يطبع يومين فقط بشكل صارم
            days.add(new WorkoutDay("Tag 1 – Ganzkörper (A)", Arrays.asList(
                    new Exercise("Kniebeugen", 4, 8),
                    new Exercise("Bankdrücken", 4, 8),
                    new Exercise("Rudern", 3, 10)
            )));
            days.add(new WorkoutDay("Tag 2 – Ganzkörper (B)", Arrays.asList(
                    new Exercise("Kreuzheben", 3, 5),
                    new Exercise("Schulterdrücken", 4, 8),
                    new Exercise("Klimmzüge", 3, 8)
            )));
        } else if (daysCount == 3) {
            // 3 أيام: يطبع ثلاثة أيام
            days.add(new WorkoutDay("Tag 1 – Ganzkörper (A)", Arrays.asList(
                    new Exercise("Kniebeugen", 4, 8),
                    new Exercise("Bankdrücken", 4, 8),
                    new Exercise("Rudern", 3, 10)
            )));
            days.add(new WorkoutDay("Tag 2 – Ganzkörper (B)", Arrays.asList(
                    new Exercise("Kreuzheben", 3, 5),
                    new Exercise("Schulterdrücken", 4, 8),
                    new Exercise("Klimmzüge", 3, 8)
            )));
            days.add(new WorkoutDay("Tag 3 – Ganzkörper (C)", Arrays.asList(
                    new Exercise("Ausfallschritte", 3, 10),
                    new Exercise("Schrägbankdrücken", 3, 10),
                    new Exercise("Latzug", 3, 10)
            )));
        } else {
            // 4 أيام أو أكثر: Oberkörper / Unterkörper كما هي في ملفك
            days.add(new WorkoutDay("Tag 1 – Oberkörper (A)", Arrays.asList(
                    new Exercise("Bankdrücken", 4, 8),
                    new Exercise("Rudern", 4, 8)
            )));
            days.add(new WorkoutDay("Tag 2 – Unterkörper (A)", Arrays.asList(
                    new Exercise("Kniebeugen", 4, 8),
                    new Exercise("Rumänisches Kreuzheben", 4, 10)
            )));
            days.add(new WorkoutDay("Tag 3 – Oberkörper (B)", Arrays.asList(
                    new Exercise("Schrägbankdrücken", 4, 8),
                    new Exercise("Klimmzüge", 4, 8)
            )));
            days.add(new WorkoutDay("Tag 4 – Unterkörper (B)", Arrays.asList(
                    new Exercise("Beinpresse", 4, 10),
                    new Exercise("Beinstrecker", 3, 12)
            )));
        }

        return new WorkoutPlan("Muskelaufbau (" + daysCount + " Tage)", days);
    }

    private static WorkoutPlan buildRecompPlan(int daysCount) {
        List<WorkoutDay> days = new ArrayList<>();

        if (daysCount == 2) {
            // يومين فقط: يطبع يومين فقط بشكل صارم
            days.add(new WorkoutDay("Tag 1 – Ganzkörper (A)", Arrays.asList(
                    new Exercise("Kniebeugen", 4, 8),
                    new Exercise("Bankdrücken", 4, 8),
                    new Exercise("Rudern", 3, 10)
            )));
            days.add(new WorkoutDay("Tag 2 – Ganzkörper (B)", Arrays.asList(
                    new Exercise("Kreuzheben", 3, 5),
                    new Exercise("Schulterdrücken", 4, 8),
                    new Exercise("Klimmzüge", 3, 8)
            )));
        } else if (daysCount == 3) {
            // 3 أيام: يطبع ثلاثة أيام
            days.add(new WorkoutDay("Tag 1 – Ganzkörper (A)", Arrays.asList(
                    new Exercise("Kniebeugen", 4, 8),
                    new Exercise("Bankdrücken", 4, 8),
                    new Exercise("Rudern", 3, 10)
            )));
            days.add(new WorkoutDay("Tag 2 – Ganzkörper (B)", Arrays.asList(
                    new Exercise("Kreuzheben", 3, 5),
                    new Exercise("Schulterdrücken", 4, 8),
                    new Exercise("Klimmzüge", 3, 8)
            )));
            days.add(new WorkoutDay("Tag 3 – Ganzkörper (C)", Arrays.asList(
                    new Exercise("Ausfallschritte", 3, 10),
                    new Exercise("Schrägbankdrücken", 3, 10),
                    new Exercise("Latzug", 3, 10)
            )));
        } else {
            // 4 أيام أو أكثر: Oberkörper / Unterkörper كما هي في ملفك
            days.add(new WorkoutDay("Tag 1 – Oberkörper (A)", Arrays.asList(
                    new Exercise("Bankdrücken", 4, 8),
                    new Exercise("Rudern", 4, 8)
            )));
            days.add(new WorkoutDay("Tag 2 – Unterkörper (A)", Arrays.asList(
                    new Exercise("Kniebeugen", 4, 8),
                    new Exercise("Rumänisches Kreuzheben", 4, 10)
            )));
            days.add(new WorkoutDay("Tag 3 – Oberkörper (B)", Arrays.asList(
                    new Exercise("Schrägbankdrücken", 4, 8),
                    new Exercise("Klimmzüge", 4, 8)
            )));
            days.add(new WorkoutDay("Tag 4 – Unterkörper (B)", Arrays.asList(
                    new Exercise("Beinpresse", 4, 10),
                    new Exercise("Beinstrecker", 3, 12)
            )));
        }

        return new WorkoutPlan("Rekomposition (" + daysCount + " Tage)", days);
    }

    private static WorkoutPlan buildFatLossPlan(int daysCount) {
        List<WorkoutDay> days = new ArrayList<>();

        if (daysCount == 2) {
            // يومين فقط: يطبع يومين فقط بشكل صارم
            days.add(new WorkoutDay("Tag 1 – Ganzkörper (A)", Arrays.asList(
                    new Exercise("Kniebeugen", 4, 8),
                    new Exercise("Bankdrücken", 4, 8),
                    new Exercise("Rudern", 3, 10)
            )));
            days.add(new WorkoutDay("Tag 2 – Ganzkörper (B)", Arrays.asList(
                    new Exercise("Kreuzheben", 3, 5),
                    new Exercise("Schulterdrücken", 4, 8),
                    new Exercise("Klimmzüge", 3, 8)
            )));
        } else if (daysCount == 3) {
            // 3 أيام: يطبع ثلاثة أيام
            days.add(new WorkoutDay("Tag 1 – Ganzkörper (A)", Arrays.asList(
                    new Exercise("Kniebeugen", 4, 8),
                    new Exercise("Bankdrücken", 4, 8),
                    new Exercise("Rudern", 3, 10)
            )));
            days.add(new WorkoutDay("Tag 2 – Ganzkörper (B)", Arrays.asList(
                    new Exercise("Kreuzheben", 3, 5),
                    new Exercise("Schulterdrücken", 4, 8),
                    new Exercise("Klimmzüge", 3, 8)
            )));
            days.add(new WorkoutDay("Tag 3 – Ganzkörper (C)", Arrays.asList(
                    new Exercise("Ausfallschritte", 3, 10),
                    new Exercise("Schrägbankdrücken", 3, 10),
                    new Exercise("Latzug", 3, 10)
            )));
        } else {
            // 4 أيام أو أكثر: Oberkörper / Unterkörper كما هي في ملفك
            days.add(new WorkoutDay("Tag 1 – Oberkörper (A)", Arrays.asList(
                    new Exercise("Bankdrücken", 4, 8),
                    new Exercise("Rudern", 4, 8)
            )));
            days.add(new WorkoutDay("Tag 2 – Unterkörper (A)", Arrays.asList(
                    new Exercise("Kniebeugen", 4, 8),
                    new Exercise("Rumänisches Kreuzheben", 4, 10)
            )));
            days.add(new WorkoutDay("Tag 3 – Oberkörper (B)", Arrays.asList(
                    new Exercise("Schrägbankdrücken", 4, 8),
                    new Exercise("Klimmzüge", 4, 8)
            )));
            days.add(new WorkoutDay("Tag 4 – Unterkörper (B)", Arrays.asList(
                    new Exercise("Beinpresse", 4, 10),
                    new Exercise("Beinstrecker", 3, 12)
            )));
        }

        return new WorkoutPlan("Fettabbau (" + daysCount + " Tage)", days);
    }
}