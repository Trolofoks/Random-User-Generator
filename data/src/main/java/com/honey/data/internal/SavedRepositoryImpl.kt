package com.honey.data.internal

import com.honey.data.model.User

class SavedRepositoryImpl(): SavedRepository {
    override suspend fun saveUser(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }
}