package com.honey.data.external

interface RandomRepository {
    suspend fun getUser(): User
    suspend fun getUsers(amount: Int): List<User>
}