package com.honey.randomusergenerator.ui.screens.favorite.contract

import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.base.ViewEvent

sealed class FavoriteEvent: ViewEvent {
    data class Favorite(val user: User, val add: Boolean): FavoriteEvent()
    data class FullInfo(val user: User): FavoriteEvent()
    object HideFullInfo: FavoriteEvent()
    object TryReload: FavoriteEvent()
}