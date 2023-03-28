package com.honey.data.internal

import com.honey.data.model.User

interface SavedRepository {
    suspend fun saveUser(user: User): Boolean
    suspend fun getAllUsers() : List<User>
    suspend fun deleteUser(user: User) : Boolean
}