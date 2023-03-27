package com.honey.data.external

import com.honey.data.model.User
import java.io.IOException


class RandomRepositoryImpl (
    private val api: RandomUserApi
) : RandomRepository {

    override suspend fun getUser(): User {
        try {
            return api.getUser()
        } catch(e: IOException) {
            throw e
        }
    }

    override suspend fun getUsers(amount: Int): List<User> {
        try {
            return api.getUsers(amount.toString())
        }catch(e: IOException) {
            throw e
        }
    }

}
