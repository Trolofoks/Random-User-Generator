package com.honey.data.external

import android.util.Log
import com.honey.data.model.GenerateKeys
import com.honey.data.model.User
import java.io.IOException


class RandomRepositoryImpl(
    private val api: RandomUserApi
) : RandomRepository {

    override suspend fun getUsers(amount: Int): List<User> {
        val response = api.getUsers(amount)
        Log.d("MyLog2", "$response")
        return response.mapToUserModel()
    }

    override suspend fun getByKey(key: String): String {
        val result = when (key) {
            GenerateKeys.AVATAR -> {
                val user = api.getAvatar().users.firstNotNullOf { it }
                user.picture.large
            }
            GenerateKeys.NAME -> {
                val user = api.getName().users.firstNotNullOf { it }
                "${user.name.firstName} ${user.name.lastName}"
            }
            GenerateKeys.EMAIL -> {
                val user = api.getEmail().users.firstNotNullOf { it }
                user.email
            }
            GenerateKeys.BIRTHDAY -> {
                val user = api.getBirthday().users.firstNotNullOf { it }
                user.dob.date
            }
            GenerateKeys.NUMBER -> {
                val user = api.getNumber().users.firstNotNullOf { it }
                user.phone
            }
            GenerateKeys.ADDRESS -> {
                val user = api.getAddress().users.firstNotNullOf { it }
                "${user.location.street.number} ${user.location.street.name}, ${user.location.city}, ${user.location.state}, ${user.location.country} ${user.location.postcode}"
            }
            else -> {
                "kekw"
            }
        }
        Log.d("MyLog2", "$result")

        return result
    }
}