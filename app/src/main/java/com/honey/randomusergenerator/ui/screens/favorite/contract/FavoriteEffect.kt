package com.honey.randomusergenerator.ui.screens.favorite.contract

import com.honey.randomusergenerator.ui.base.ViewEffect

sealed class FavoriteEffect : ViewEffect {
    object NavBack : FavoriteEffect()
}