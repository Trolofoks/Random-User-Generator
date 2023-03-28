package com.honey.data.external

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("results") val users: List<ApiUser>,
)

data class ApiUser(
    @SerializedName("name") val name: ApiUserName,
    @SerializedName("location") val location: ApiUserLocation,
    @SerializedName("email") val email: String,
    @SerializedName("login") val login: ApiUserLogin,
    @SerializedName("dob") val dob: ApiUserDob,
    @SerializedName("registered") val registered: ApiUserRegistered,
    @SerializedName("phone") val phone: String,
    @SerializedName("cell") val cell: String,
    @SerializedName("id") val id: ApiUserId,
    @SerializedName("picture") val picture: ApiUserPicture,
    @SerializedName("nat") val nationality: String
)

data class ApiUserName(
    @SerializedName("title") val title: String,
    @SerializedName("first") val firstName: String,
    @SerializedName("last") val lastName: String
)

data class ApiUserLocation(
    @SerializedName("street") val street: ApiUserStreet,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("postcode") val postcode: String,
    @SerializedName("coordinates") val coordinates: ApiUserCoordinates,
    @SerializedName("timezone") val timezone: ApiUserTimezone
)

data class ApiUserStreet(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String
)

data class ApiUserCoordinates(
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String
)

data class ApiUserTimezone(
    @SerializedName("offset") val offset: String,
    @SerializedName("description") val description: String
)

data class ApiUserLogin(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("salt") val salt: String,
    @SerializedName("md5") val md5: String,
    @SerializedName("sha1") val sha1: String,
    @SerializedName("sha256") val sha256: String
)

data class ApiUserDob(
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int
)

data class ApiUserRegistered(
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int
)

data class ApiUserId(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: String
)

data class ApiUserPicture(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String
)