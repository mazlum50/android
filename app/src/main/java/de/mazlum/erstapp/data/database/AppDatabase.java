package de.mazlum.erstapp.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import de.mazlum.erstapp.data.dao.UserDao;
import de.mazlum.erstapp.data.entity.User;

@Database(
        entities = {User.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract UserDao userDao();
    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "gym_database"
            ).build();
        }
        return instance;
    }
}
