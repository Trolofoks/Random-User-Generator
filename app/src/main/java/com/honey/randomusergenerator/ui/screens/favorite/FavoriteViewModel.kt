package com.honey.randomusergenerator.ui.screens.favorite

import com.honey.data.internal.SavedRepository
import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEffect
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEvent
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteState

class FavoriteViewModel(
    private val savedRepository: SavedRepository
): BaseViewModel<FavoriteEvent,FavoriteState,FavoriteEffect>(
    initialState = FavoriteState.Loading
){
    override fun obtainEvent(event: FavoriteEvent) {
        when(val state = viewState){
            is FavoriteState.Empty -> {reduce(event, state)}
            is FavoriteState.Loading -> {reduce(event, state)}
            is FavoriteState.ShowFav -> {reduce(event, state)}
        }
    }

    private fun reduce(event: FavoriteEvent, currentState: FavoriteState.Loading){

    }
    private fun reduce(event: FavoriteEvent, currentState: FavoriteState.ShowFav){

    }
    private fun reduce(event: FavoriteEvent, currentState: FavoriteState.Empty){

    }
}