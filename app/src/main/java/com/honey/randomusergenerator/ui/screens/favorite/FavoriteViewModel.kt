package com.honey.randomusergenerator.ui.screens.favorite

import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.honey.data.internal.SavedRepository
import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.extensions.removeFromRepo
import com.honey.randomusergenerator.extensions.saveToRepo
import com.honey.randomusergenerator.extensions.toAppUsers
import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEffect
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteEvent
import com.honey.randomusergenerator.ui.screens.favorite.contract.FavoriteState
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val savedRepository: SavedRepository
): BaseViewModel<FavoriteEvent,FavoriteState,FavoriteEffect>(
    initialState = FavoriteState.Loading
){
    init {
        performLoadUser()
    }

    override fun obtainEvent(event: FavoriteEvent) {
        when(val state = viewState){
            is FavoriteState.Empty -> {reduce(event, state)}
            is FavoriteState.Loading -> {reduce(event, state)}
            is FavoriteState.ShowFav -> {reduce(event, state)}
        }
    }

    private fun reduce(event: FavoriteEvent, currentState: FavoriteState.Loading){
        when(event){
            is FavoriteEvent.TryReload -> {
                performLoadUser()
            }
            else -> {}
        }
    }
    private fun reduce(event: FavoriteEvent, currentState: FavoriteState.ShowFav){
        when(event){
            is FavoriteEvent.TryReload -> {
                performLoadUser()
            }
            is FavoriteEvent.FullInfo -> {
                viewState = currentState.copy(fullInfoUser = event.user)
            }
            is FavoriteEvent.HideFullInfo -> {
                viewState = currentState.copy(fullInfoUser = null)
            }
            is FavoriteEvent.Favorite -> {
                performFavorite(event.user, event.add, currentState)
            }
            else -> {}
        }
    }
    private fun reduce(event: FavoriteEvent, currentState: FavoriteState.Empty){
        when(event){
            is FavoriteEvent.TryReload -> {
                performLoadUser()
            }
            else -> {}
        }
    }

    private fun performFavorite(user: User, add: Boolean,state: FavoriteState.ShowFav){
        viewModelScope.launch {
            if (add){
                user.saveToRepo(savedRepository)
            } else {
                user.removeFromRepo(savedRepository)
            }
            performLoadUser(currentFullInfo = state.fullInfoUser)
        }
    }

    private fun performLoadUser(currentFullInfo: User? = null){
        viewModelScope.launch {
            val users = loadUsers()

             if (users.isNotEmpty()){
                viewState = FavoriteState.ShowFav(users, currentFullInfo)
            } else {
                viewState = FavoriteState.Empty
             }
        }
    }

    private suspend fun loadUsers(): List<User>{
        return savedRepository.getAllUsers().toAppUsers()
    }
}