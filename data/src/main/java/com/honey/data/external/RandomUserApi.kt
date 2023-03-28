package com.honey.data.external

import com.honey.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RandomUserApi {
    @GET(Endpoints.BASE)
    suspend fun getUsers(@Query("results") amount: Int): ApiResponse


}