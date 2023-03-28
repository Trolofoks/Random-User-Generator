package com.honey.data.external

import android.util.Log
import com.honey.data.model.User
import java.io.IOException


class RandomRepositoryImpl (
    private val api: RandomUserApi
) : RandomRepository {

    override suspend fun getUsers(amount: Int): List<User> {
        val response = api.getUsers(amount)
        Log.d("MyLog2","$response")
        return RandomUserMapper().mapToDomain(response)
    }
}
