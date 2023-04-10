package com.honey.data.internal.savedusers

import android.util.Log
import com.honey.data.internal.savedusers.sql.SavedDatabase
import com.honey.data.internal.savedusers.sql.UserDao
import com.honey.data.model.User

class SavedRepositoryImpl(private val savedDatabase: SavedDatabase): SavedRepository {
    private val dao: UserDao by lazy { savedDatabase.userDao() }

    override suspend fun saveUser(user: User): Boolean {
        Log.d("MyLog","user to save -> $user")
        dao.saveUser(user)
        return true
    }

    override suspend fun getAllUsers(): List<User> {
        return dao.getAllUsers() ?: emptyList()
    }

    override suspend fun deleteUser(user: User): Boolean {
        Log.d("MyLog","user to delete -> $user")
        dao.deleteUser(user)
        return true
    }
}