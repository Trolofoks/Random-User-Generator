package com.honey.randomusergenerator.ui.screens.generator

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.honey.data.external.RandomRepository
import com.honey.data.internal.SavedRepository
import com.honey.randomusergenerator.data.model.User
import com.honey.randomusergenerator.extensions.removeFromRepo
import com.honey.randomusergenerator.extensions.saveToRepo
import com.honey.randomusergenerator.extensions.toAppUsers
import com.honey.randomusergenerator.extensions.toDataUser
import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEffect
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEvent
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GeneratorViewModel(
    private val randomRepository: RandomRepository,
    private val savedRepository: SavedRepository
):BaseViewModel<GeneratorEvent,GeneratorState,GeneratorEffect>(initialState = GeneratorState.Empty) {

    override fun obtainEvent(event: GeneratorEvent) {
        when(val state = viewState){
            is GeneratorState.Generating -> reduce(event, state)
            is GeneratorState.ShowUsers -> reduce(event, state)
            is GeneratorState.Error -> reduce(event, state)
            is GeneratorState.Empty -> reduce(event, state)
        }
    }

    private fun reduce(event: GeneratorEvent, currentState: GeneratorState.Generating){

    }
    private fun reduce(event: GeneratorEvent, currentState: GeneratorState.ShowUsers){
        when(event){
            is GeneratorEvent.Favorite -> {
                performFavoriteClick(event.user, event.add)
            }
            is GeneratorEvent.FullInfoClick -> {
                performFullInfoClick(event.user, currentState)
            }
            is GeneratorEvent.HideFullInfo -> {
                performHideFullInfo(currentState)
            }
            is GeneratorEvent.Regenerate -> {
                performServerQuery(amount = event.amount)
            }

            else -> {}
        }
    }
    private fun reduce(event: GeneratorEvent, currentState: GeneratorState.Error){
        when(event){
            is GeneratorEvent.Refresh -> {}
            else -> {}
        }
    }
    private fun reduce(event: GeneratorEvent, currentState: GeneratorState.Empty){
        when(event){
            is GeneratorEvent.Generate -> {
                performServerQuery(event.amount)
            }
            else -> {}
        }
    }

    private fun performFavoriteClick(user: User, add: Boolean){
        viewModelScope.launch {
            if (add){
                val saved = user.saveToRepo(savedRepository)
                Log.d("MyLog", "perform saving, result: $saved")
            } else {
                val removed = user.removeFromRepo(savedRepository)
                Log.d("MyLog", "perform removing, result: $removed")
            }
        }

    }

    private fun performFullInfoClick(user: User, currentState: GeneratorState.ShowUsers){
        currentState.let {
            viewState = GeneratorState.ShowUsers(users = it.users, selectedUser = user)
        }
    }

    private fun performHideFullInfo(currentState: GeneratorState.ShowUsers){
        currentState.let {
            viewState = GeneratorState.ShowUsers(users = it.users, selectedUser = null)
        }
    }

    private fun performServerQuery(amount: Int){
        viewState = GeneratorState.Generating
        viewModelScope.launch {
            val gotUsers = randomRepository.getUsers(amount)
            if (gotUsers.isNotEmpty()){
                delay(1000)
                viewState = GeneratorState.ShowUsers(gotUsers.toAppUsers())
            }
        }
    }
}