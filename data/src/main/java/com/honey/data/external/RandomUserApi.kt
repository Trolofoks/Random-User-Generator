package com.honey.data.external

import com.honey.data.external.Endpoints
import com.honey.data.external.User
import retrofit2.http.GET
import retrofit2.http.Path

interface RandomUserApi {
    @GET()
    suspend fun getUser(): User

    @GET(Endpoints.AMOUNT)
    suspend fun getUsers(@Path("value") amount: String): List<User>

}