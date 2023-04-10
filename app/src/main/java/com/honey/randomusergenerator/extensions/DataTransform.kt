package com.honey.randomusergenerator.extensions

import androidx.lifecycle.ViewModel
import com.honey.randomusergenerator.data.model.User

fun com.honey.data.model.User.toAppUser(): User {
    return User(
        avatarURL = pictureUrl.orEmpty(),
        name = name,
        email = email,
        birthday = birthday,
        address = address,
        number = number,
        password = password,
        picture = picture
    )
}

fun User.toDataUser(): com.honey.data.model.User {
    return com.honey.data.model.User(
        pictureUrl = avatarURL,
        name = name,
        email = email,
        birthday = birthday,
        address = address,
        number = number,
        password = password,
        picture = picture
    )
}

fun List<com.honey.data.model.User>.toAppUsers(): List<User> {
    return map { userData ->
        userData.toAppUser()
    }
}

fun List<User>.toDataUsers(): List<com.honey.data.model.User> {
    return map { userApp ->
        userApp.toDataUser()
    }
}

