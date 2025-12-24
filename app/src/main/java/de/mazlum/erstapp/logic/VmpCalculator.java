package de.mazlum.erstapp.logic;


import de.mazlum.erstapp.model.UserGoal;
import de.mazlum.erstapp.model.VmpCategory;

public class VmpCalculator {


        public static VmpCategory calculate(
                UserGoal goal,
                float weightKg,
                float heightCm
        ) {

            float heightM = heightCm / 100f;
            float bmi = weightKg / (heightM * heightM);

            // المستخدم يريد بناء عضلات
            if (goal == UserGoal.MUSCLE) {

                if (bmi > 25f) {
                    return VmpCategory.RECOMP;
                } else {
                    return VmpCategory.MUSCLE_BUILD;
                }
            }

            // المستخدم يريد خسارة وزن
            if (goal == UserGoal.FAT_LOSS) {

                if (bmi > 25f) {
                    return VmpCategory.FAT_LOSS;
                } else {
                    return VmpCategory.RECOMP;
                }
            }

            // fallback (يجب ألا يحدث)
            return VmpCategory.RECOMP;
        }
}
