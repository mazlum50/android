package de.mazlum.erstapp.data.database;

import androidx.room.TypeConverter;

import de.mazlum.erstapp.model.UserGoal;

public class Converters {
    @TypeConverter
    public static String fromUserGoal(UserGoal userGoal){
        return userGoal == null ? null :userGoal.name();
    }
    @TypeConverter
    public static UserGoal toUserGoal(String userGoal){
        if (userGoal.equalsIgnoreCase("bulk")){
            return UserGoal.MUSCLE;
        }
        return userGoal == null ? null :UserGoal.valueOf(userGoal);
    }
}
