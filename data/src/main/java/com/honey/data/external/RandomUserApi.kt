package com.honey.data.external

import com.honey.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RandomUserApi {
    @GET(Endpoints.BASE)
    suspend fun getUsers(@Query("results") amount: Int): ApiResponse

    @GET(Endpoints.AVATAR_ONLY)
    suspend fun getAvatar() : ApiResponse

    @GET(Endpoints.NAME_ONLY)
    suspend fun getName() : ApiResponse

    @GET(Endpoints.EMAIL_ONLY)
    suspend fun getEmail() : ApiResponse

    @GET(Endpoints.BIRTHDAY_ONLY)
    suspend fun getBirthday() : ApiResponse

    @GET(Endpoints.ADDRESS_ONLY)
    suspend fun getAddress() : ApiResponse

    @GET(Endpoints.NUMBER_ONLY)
    suspend fun getNumber() : ApiResponse
}