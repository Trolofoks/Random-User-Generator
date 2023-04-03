package com.honey.data.external

import androidx.lifecycle.Transformations.map
import com.honey.data.model.User

fun ApiResponse.firstToPicture(): String{
    val result = users.map {
        it.picture.large
    }
    return result.first()
}

fun ApiResponse.mapToUserModel(): List<User>{
    return users.map { randomUser ->
        User(
            pictureUrl = randomUser.picture.large,
            name = "${randomUser.name.firstName} ${randomUser.name.lastName}",
            email = randomUser.email,
            birthday = randomUser.dob.date,
            address = "${randomUser.location.street.number} ${randomUser.location.street.name}, ${randomUser.location.city}, ${randomUser.location.state}, ${randomUser.location.country} ${randomUser.location.postcode}",
            number = randomUser.phone,
            password = randomUser.login.password
        )
    }
}
