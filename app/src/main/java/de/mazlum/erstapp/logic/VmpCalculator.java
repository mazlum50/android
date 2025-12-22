package de.mazlum.erstapp.logic;

import de.mazlum.erstapp.R;

public class VmpCalculator {
    public static VmpCategory getVmpCategory (int  weight ,int  height ){
        double ratio = (double)weight /height;
        if (ratio < 0.40){
                return VmpCategory.MUSCLE_BUILD;
            }else if (ratio < 0.50){
                return VmpCategory.RECOMP;
            }else {
            return VmpCategory.FAT_LOSS;
        }
        }
}
