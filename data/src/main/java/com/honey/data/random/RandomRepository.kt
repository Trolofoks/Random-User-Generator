package com.honey.data.random

import com.honey.data.model.User

interface RandomRepository {
    suspend fun getUsers(amount: Int): List<User>
    suspend fun getByKey(Key: String): String
}