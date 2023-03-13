package com.honey.randomusergenerator.data.model

import android.provider.ContactsContract.CommonDataKinds.Nickname

data class User(
    val nickname: String,
    val number: String? = null,
)
