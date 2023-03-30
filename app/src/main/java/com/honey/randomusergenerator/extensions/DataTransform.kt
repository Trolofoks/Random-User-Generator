package com.honey.randomusergenerator.extensions

import androidx.lifecycle.ViewModel
import com.honey.randomusergenerator.data.model.User

fun com.honey.data.model.User.toAppUser(): User {
    return User(
        avatarURL = pictureUrl.orEmpty(),
        name = name.orEmpty(),
        email = email.orEmpty(),
        birthday = birthday.orEmpty(),
        address = address.orEmpty(),
        number = number.orEmpty(),
        password = password.orEmpty()
    )
}

fun User.toDataUser(): com.honey.data.model.User {
    return com.honey.data.model.User(
        pictureUrl = avatarURL.orEmpty(),
        name = name.orEmpty(),
        email = email.orEmpty(),
        birthday = birthday.orEmpty(),
        address = address.orEmpty(),
        number = number.orEmpty(),
        password = password.orEmpty()
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



//fun ViewModel.fromDataToApp(userData: com.honey.data.model.User): User {
//    return User(
//        avatarURL = userData.pictureUrl,
//        name = userData.name,
//        email = userData.email,
//        birthday = userData.birthday,
//        address = userData.address,
//        number = userData.number,
//        password = userData.password
//    )
//}
//
//fun ViewModel.fromAppToData(userApp: User): com.honey.data.model.User{
//    return com.honey.data.model.User(
//        id = null,
//        pictureUrl = userApp.avatarURL,
//        name = userApp.name,
//        email = userApp.email,
//        birthday = userApp.birthday,
//        address = userApp.address,
//        number = userApp.number,
//        password = userApp.password
//    )
//}
