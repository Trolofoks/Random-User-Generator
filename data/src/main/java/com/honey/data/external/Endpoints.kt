package com.honey.data.external

object Endpoints {
    const val BASE_URL = "https://randomuser.me/api/"
    //?some&some&

    const val BASE = "?"

    const val AVATAR_ONLY = "?inc=picture"
    const val NAME_ONLY = "?inc=name"
    const val EMAIL_ONLY = "?inc=email"
    const val BIRTHDAY_ONLY = "?inc=dob"
    const val ADDRESS_ONLY = "?inc=location"
    const val NUMBER_ONLY = "?inc=phone"

    //up to 5000
    const val AMOUNT = "?results={value}"
    //male / female
    const val GENDER = "gender={value}"
    //AU, BR, CA, CH, DE, DK, ES, FI, FR, GB, IE, IN, IR, MX, NL, NO, NZ, RS, TR, UA, US
    const val NATION = "nat={value}"
    //gender name location email login registered dob phone cell id picture nat (,)
    const val INCLUDE_ONLY = "inc={value}"
}