package com.honey.randomusergenerator.ui.screens.favorite.contract

import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.base.ViewState

sealed class FavoriteState : ViewState {
    object EmptyUsersList : FavoriteState()
    data class ShowUsers(val users: List<User>) : FavoriteState()
}