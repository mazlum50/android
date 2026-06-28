package de.mazlum.erstapp.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.mazlum.erstapp.model.Exercise;
import de.mazlum.erstapp.model.VmpCategory;
import de.mazlum.erstapp.model.WorkoutDay;
import de.mazlum.erstapp.model.WorkoutPlan;

public class WorkoutLogic {

    // Hauptmethode zur Generierung des Trainingsplans basierend auf Kategorie und Tagen
    public static WorkoutPlan getWorkoutPlan(VmpCategory category, int trainingDays) {
        String titlePrefix;
        boolean isBurnFocus = (category == VmpCategory.FAT_LOSS || category == VmpCategory.RECOMP);

        switch (category) {
            case MUSCLE_BUILD:
                titlePrefix = "Muskelaufbau";
                break;
            case RECOMP:
                titlePrefix = "Rekomposition";
                break;
            case FAT_LOSS:
                titlePrefix = "Fettabbau";
                break;
            default:
                throw new IllegalStateException("Unbekannte VMP-Kategorie");
        }

        // Generiert ein vollständiges und ausgefeiltes Trainingsprogramm
        List<WorkoutDay> days = generateSmartDays(trainingDays, isBurnFocus);
        return new WorkoutPlan(titlePrefix + " (" + trainingDays + " Tage)", days);
    }

    // Hilfsmethode zur Erstellung der Tage mit einem vollwertigen Übungskatalog
    private static List<WorkoutDay> generateSmartDays(int daysCount, boolean isBurnFocus) {
        List<WorkoutDay> days = new ArrayList<>();

        // Parametrisierung basierend auf dem primären Ziel
        // Muskelaufbau: Höhere Intensität (3 Sätze, 8 Wdh)
        // Fettabbau/Recomp: Höheres Volumen zur Kalorienverbrennung (4 Sätze, 12 Wdh)
        int defaultSets = isBurnFocus ? 4 : 3;
        int defaultReps = isBurnFocus ? 12 : 8;

        switch (daysCount) {
            case 2:
                // 2 Tage: Ganzkörper (Komplettes Spektrum für den ganzen Körper)
                days.add(new WorkoutDay("Tag 1 – Ganzkörper (A)", Arrays.asList(
                        new Exercise("Kniebeugen", defaultSets, defaultReps),
                        new Exercise("Bankdrücken", defaultSets, defaultReps),
                        new Exercise("Rudern am Kabelzug", defaultSets, defaultReps),
                        new Exercise("Schulterdrücken (Kurzhantel)", defaultSets, defaultReps),
                        new Exercise("Wadenheben", 3, 15),
                        new Exercise("Beinheben (Bauch)", 3, 15)
                )));
                days.add(new WorkoutDay("Tag 2 – Ganzkörper (B)", Arrays.asList(
                        new Exercise("Kreuzheben", 3, isBurnFocus ? 8 : 5),
                        new Exercise("Klimmzüge (oder Latzug)", defaultSets, defaultReps),
                        new Exercise("Schrägbankdrücken", defaultSets, defaultReps),
                        new Exercise("Beinpresse", defaultSets, defaultReps),
                        new Exercise("Seitheben", 3, 12),
                        new Exercise("Plank", 3, 60) // 60 Sekunden Haltezeit
                )));
                break;

            case 3:
                // 3 Tage: Push / Pull / Legs (Klassische Dreier-Aufteilung)
                days.add(new WorkoutDay("Tag 1 – Push (Brust/Schulter/Trizeps)", Arrays.asList(
                        new Exercise("Bankdrücken", defaultSets, defaultReps),
                        new Exercise("Schulterdrücken (Langhantel)", defaultSets, defaultReps),
                        new Exercise("Schrägbankdrücken (Kurzhantel)", defaultSets, defaultReps),
                        new Exercise("Seitheben", 3, 12),
                        new Exercise("Trizepsdrücken am Kabel", 3, 10),
                        new Exercise("Dips", 3, 10)
                )));
                days.add(new WorkoutDay("Tag 2 – Pull (Rücken/Hintere Schulter/Bizeps)", Arrays.asList(
                        new Exercise("Klimmzüge", defaultSets, defaultReps),
                        new Exercise("Langhantel-Rudern", defaultSets, defaultReps),
                        new Exercise("Latzug zur Brust", defaultSets, defaultReps),
                        new Exercise("Face Pulls", 3, 15),
                        new Exercise("Bizeps Curls (Kurzhantel)", 3, 10),
                        new Exercise("Hammer Curls", 3, 10)
                )));
                days.add(new WorkoutDay("Tag 3 – Legs (Oberschenkel/Waden/Bauch)", Arrays.asList(
                        new Exercise("Kniebeugen", defaultSets, defaultReps),
                        new Exercise("Rumänisches Kreuzheben", defaultSets, defaultReps),
                        new Exercise("Beinpresse", defaultSets, defaultReps),
                        new Exercise("Beinstrecker", 3, 12),
                        new Exercise("Wadenheben (stehend)", 4, 15),
                        new Exercise("Crunches", 3, 20)
                )));
                break;

            case 4:
                // 4 Tage: Oberkörper / Unterkörper Split (Optimal für Frequenz)
                days.add(new WorkoutDay("Tag 1 – Oberkörper (A)", Arrays.asList(
                        new Exercise("Bankdrücken", defaultSets, defaultReps),
                        new Exercise("Rudern am Kabel", defaultSets, defaultReps),
                        new Exercise("Schulterdrücken", defaultSets, defaultReps),
                        new Exercise("Latzug", defaultSets, defaultReps),
                        new Exercise("Bizeps/Trizeps Supersatz", 3, 10)
                )));
                days.add(new WorkoutDay("Tag 2 – Unterkörper (A)", Arrays.asList(
                        new Exercise("Kniebeugen", defaultSets, defaultReps),
                        new Exercise("Rumänisches Kreuzheben", defaultSets, defaultReps),
                        new Exercise("Ausfallschritte", 3, 12),
                        new Exercise("Wadenheben", 4, 15)
                )));
                days.add(new WorkoutDay("Tag 3 – Oberkörper (B)", Arrays.asList(
                        new Exercise("Schrägbankdrücken", defaultSets, defaultReps),
                        new Exercise("Klimmzüge", defaultSets, defaultReps),
                        new Exercise("Dips", defaultSets, defaultReps),
                        new Exercise("Kurzhantel-Rudern", defaultSets, defaultReps),
                        new Exercise("Seitheben", 3, 12)
                )));
                days.add(new WorkoutDay("Tag 4 – Unterkörper (B)", Arrays.asList(
                        new Exercise("Beinpresse", defaultSets, defaultReps),
                        new Exercise("Beinbeuger (sitzend)", defaultSets, defaultReps),
                        new Exercise("Beinstrecker", 3, 12),
                        new Exercise("Wadenheben (sitzend)", 4, 15)
                )));
                break;

            case 5:
                // 5 Tage: Push/Pull/Legs + Upper/Lower (Fortgeschrittenen-Mix)
                days.add(new WorkoutDay("Tag 1 – Push", Arrays.asList(
                        new Exercise("Bankdrücken", defaultSets, defaultReps),
                        new Exercise("Schulterdrücken", defaultSets, defaultReps),
                        new Exercise("Fliessende Dips", 3, 10),
                        new Exercise("Seitheben", 3, 12)
                )));
                days.add(new WorkoutDay("Tag 2 – Pull", Arrays.asList(
                        new Exercise("Rudern", defaultSets, defaultReps),
                        new Exercise("Klimmzüge", defaultSets, defaultReps),
                        new Exercise("Latzug", defaultSets, defaultReps),
                        new Exercise("Bizeps Curls", 3, 10)
                )));
                days.add(new WorkoutDay("Tag 3 – Legs", Arrays.asList(
                        new Exercise("Kniebeugen", defaultSets, defaultReps),
                        new Exercise("Beinpresse", 3, 10),
                        new Exercise("Beinbeuger", defaultSets, defaultReps),
                        new Exercise("Wadenheben", 4, 15)
                )));
                days.add(new WorkoutDay("Tag 4 – Oberkörper", Arrays.asList(
                        new Exercise("Schrägbankdrücken", defaultSets, defaultReps),
                        new Exercise("Rudern am Kabelzug", defaultSets, defaultReps),
                        new Exercise("Schulterdrücken (Kurzhantel)", defaultSets, defaultReps),
                        new Exercise("Face Pulls", 3, 15)
                )));
                days.add(new WorkoutDay("Tag 5 – Unterkörper", Arrays.asList(
                        new Exercise("Rumänisches Kreuzheben", defaultSets, defaultReps),
                        new Exercise("Beinstrecker", 3, 12),
                        new Exercise("Ausfallschritte", 3, 12),
                        new Exercise("Wadenheben", 4, 15)
                )));
                break;

            case 6:
                // 6 Tage: Push / Pull / Legs (X2) (Hochintensiver doppelter Zyklus)
                days.add(new WorkoutDay("Tag 1 – Push (A)", Arrays.asList(
                        new Exercise("Bankdrücken", defaultSets, defaultReps),
                        new Exercise("Schulterdrücken", defaultSets, defaultReps),
                        new Exercise("Trizepsdrücken", 3, 10)
                )));
                days.add(new WorkoutDay("Tag 2 – Pull (A)", Arrays.asList(
                        new Exercise("Langhantel-Rudern", defaultSets, defaultReps),
                        new Exercise("Klimmzüge", defaultSets, defaultReps),
                        new Exercise("Bizeps Curls", 3, 10)
                )));
                days.add(new WorkoutDay("Tag 3 – Legs (A)", Arrays.asList(
                        new Exercise("Kniebeugen", defaultSets, defaultReps),
                        new Exercise("Rumänisches Kreuzheben", defaultSets, defaultReps),
                        new Exercise("Wadenheben", 4, 15)
                )));
                days.add(new WorkoutDay("Tag 4 – Push (B)", Arrays.asList(
                        new Exercise("Schrägbankdrücken", defaultSets, defaultReps),
                        new Exercise("Seitheben", 3, 12),
                        new Exercise("Dips", 3, 10)
                )));
                days.add(new WorkoutDay("Tag 5 – Pull (B)", Arrays.asList(
                        new Exercise("Rudern am Kabel", defaultSets, defaultReps),
                        new Exercise("Latzug zur Brust", defaultSets, defaultReps),
                        new Exercise("Hammer Curls", 3, 10)
                )));
                days.add(new WorkoutDay("Tag 6 – Legs (B)", Arrays.asList(
                        new Exercise("Beinpresse", defaultSets, defaultReps),
                        new Exercise("Beinstrecker", 3, 12),
                        new Exercise("Beinbeuger", defaultSets, defaultReps)
                )));
                break;

            default:
                // Fallback-Option für nicht vordefinierte Werte
                days.add(new WorkoutDay("Tag 1 – Ganzkörper Basis", Arrays.asList(
                        new Exercise("Kniebeugen", 3, 10),
                        new Exercise("Bankdrücken", 3, 10),
                        new Exercise("Rudern", 3, 10)
                )));
                break;
        }

        return days;
    }
}