package de.mazlum.erstapp.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import de.mazlum.erstapp.model.UserGoal;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "weight")
    private int weight;

    @ColumnInfo(name = "height")
    private int height;
    @ColumnInfo(name = "goal")
    private UserGoal goal;
    @ColumnInfo(name = "trainingDays")
    private int trainingDays;


    public User(UserGoal goal, int height, int weight) {
        this.goal = goal;
        this.height = height;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public UserGoal getGoal() {
        return goal;
    }

    public void setGoal(UserGoal goal) {
        this.goal = goal;
    }

    public int getTrainingDays() {
        return trainingDays;
    }

    public void setTrainingDays(int trainingDays) {
        this.trainingDays = trainingDays;
    }
}
