package de.mazlum.erstapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import de.mazlum.erstapp.data.entity.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);
    @Query("SELECT * FROM users LIMIT 1")
    User getUser();

}
