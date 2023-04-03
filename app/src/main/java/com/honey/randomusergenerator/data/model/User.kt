package com.honey.randomusergenerator.data.model

import android.provider.ContactsContract.CommonDataKinds.Nickname

data class User(
    val avatarURL: String,
    val name: String,
    val email: String,
    val birthday: String,
    val address: String,
    val number: String,
    val password: String,
    val picture : ByteArray? = null
)
