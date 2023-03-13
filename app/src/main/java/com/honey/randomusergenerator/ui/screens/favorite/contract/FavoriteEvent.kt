package com.honey.randomusergenerator.ui.screens.favorite.contract

import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.base.ViewEvent

sealed class FavoriteEvent: ViewEvent {
    data class DeleteUser(val user: User): FavoriteEvent()
    data class OpenUser(val user: User): FavoriteEvent()
}