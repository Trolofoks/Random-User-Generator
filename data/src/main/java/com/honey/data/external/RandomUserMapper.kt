package com.honey.data.external

import com.honey.data.model.User


class RandomUserMapper {
    fun mapToDomain(response: ApiResponse): List <User> {
        return response.users.map { randomUser ->
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
}