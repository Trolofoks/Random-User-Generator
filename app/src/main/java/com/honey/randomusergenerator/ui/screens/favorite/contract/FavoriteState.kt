package com.honey.randomusergenerator.ui.screens.favorite.contract

import com.honey.data.model.Language
import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.ui.base.ViewState

sealed class FavoriteState : ViewState {
    object Empty : FavoriteState()
    object Loading: FavoriteState()
    data class ShowFav(val users: List<User>, val fullInfoUser: User? = null, val exportLanguage: String) : FavoriteState()
}