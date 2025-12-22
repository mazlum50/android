package de.mazlum.erstapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import de.mazlum.erstapp.data.entity.User;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);
    @Query("SELECT * FROM users LIMIT 1")
    User getUser();

}
