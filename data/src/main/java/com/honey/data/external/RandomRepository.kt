package com.honey.data.external

import com.honey.data.model.User

interface RandomRepository {
    suspend fun getUsers(amount: Int): List<User>
}