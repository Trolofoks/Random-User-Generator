package com.honey.data.model


data class User(
    val picture: String,
    val name: String,
    val email: String,
    val birthday: String,
    val address: String,
    val number: String,
    val password: String
)

@Entity(tableName = "users")
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
