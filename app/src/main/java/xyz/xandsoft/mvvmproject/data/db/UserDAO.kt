package xyz.xandsoft.mvvmproject.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(users: Users): Long

    @Query("SELECT * FROM Users WHERE uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<Users>
}