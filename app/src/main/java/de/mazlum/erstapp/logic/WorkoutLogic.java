package de.mazlum.erstapp.logic;

import de.mazlum.erstapp.model.WorkoutPlan;

public class WorkoutLogic {
    public static WorkoutPlan getWorkoutPlan (VmpCategory vmpCategory ){
       switch (vmpCategory) {
           case MUSCLE_BUILD:
               return new WorkoutPlan(
                       "Muskelaufbau",
                       "3x pro Woche",
                       "Grundübungen (Ganzkörper)",
                       "8–12 Wiederholungen, progressive Steigerung"
               );

           case RECOMP:
               return new WorkoutPlan(
                       "Rekomposition",
                       "4x pro Woche",
                       "Upper / Lower Split",
                       "Krafttraining + leichtes Cardio"
               );

           case FAT_LOSS:
               return new WorkoutPlan(
                       "Fettabbau",
                       "4–5x pro Woche",
                       "Zirkeltraining + Cardio",
                       "Kurze Pausen, hohe Intensität"
               );

           default:
               return null;
       }
    }
    public static String getVmpTitleDe(VmpCategory category) {

        switch (category) {

            case MUSCLE_BUILD:
                return "Muskelaufbau";

            case RECOMP:
                return "Rekomposition";

            case FAT_LOSS:
                return "Fettabbau";

            default:
                return "";
        }
    }
}
