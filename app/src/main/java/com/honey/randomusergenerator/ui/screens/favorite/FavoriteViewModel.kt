package com.honey.randomusergenerator.ui.screens.favorite

import androidx.lifecycle.ViewModel
import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEffect
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEvent
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteState

class FavoriteViewModel(

): BaseViewModel<FavoriteEvent,FavoriteState,FavoriteEffect>(
    initialState = FavoriteState.Loading
){
    override fun obtainEvent(event: FavoriteEvent) {
        when(viewState){
            is FavoriteState.EmptyUsersList -> {reduce(event, viewState as FavoriteState.EmptyUsersList)}
            is FavoriteState.Loading -> {reduce(event, viewState as FavoriteState.Loading)}
            is FavoriteState.ShowUsers -> {reduce(event, viewState as FavoriteState.ShowUsers)}
        }
    }

    private fun reduce(event: FavoriteEvent, currentState: FavoriteState.Loading){

    }
    private fun reduce(event: FavoriteEvent, currentState: FavoriteState.ShowUsers){

    }
    private fun reduce(event: FavoriteEvent, currentState: FavoriteState.EmptyUsersList){

    }
}