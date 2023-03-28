package com.honey.data.internal.sql

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.honey.data.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: User): Long

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>?
}