package com.honey.data.external

import com.honey.data.model.User

interface RandomRepository {
    suspend fun getUser(): User
    suspend fun getUsers(amount: Int): List<User>
}