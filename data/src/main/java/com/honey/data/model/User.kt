package com.honey.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = Constance.TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @SerializedName("picture") val pictureUrl: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("dob") val birthday: String,
    @SerializedName("location") val address: String,
    @SerializedName("phone") val number: String,
    @SerializedName("password") val password: String
)
