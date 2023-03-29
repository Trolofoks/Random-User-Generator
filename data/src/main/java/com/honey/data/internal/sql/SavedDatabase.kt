package com.honey.data.internal.sql

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.honey.data.model.Constance
import com.honey.data.model.User

@Database(entities = [User::class], version =1)
abstract class SavedDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    //Can implement in DI with replace "companion object" -> "single"
    companion object {
        fun getDatabase(context: Context): SavedDatabase{
            return Room.databaseBuilder(
                context.applicationContext,
                SavedDatabase::class.java,
                Constance.DATABASE_NAME
            ).build()
        }
    }
}