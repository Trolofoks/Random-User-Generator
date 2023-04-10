package com.honey.data.saved.storage.sql

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.honey.data.model.User

@Database(entities = [User::class], version = 2)
abstract class SavedDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    //Can implement in DI with replace "companion object" -> "single"
    companion object {
        fun getDatabase(context: Context): SavedDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                SavedDatabase::class.java,
                Constance.DATABASE_NAME
            ).build()
        }
    }
}