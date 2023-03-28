package com.honey.randomusergenerator.ui.screens.generator

import androidx.lifecycle.viewModelScope
import com.honey.data.external.RandomRepository
import com.honey.data.internal.SavedRepository
import com.honey.randomusergenerator.extensions.toAppUser
import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEffect
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEvent
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorState
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
            is GeneratorEvent.Favorite -> {}
            is GeneratorEvent.FullInfoClick -> {}
            is GeneratorEvent.HideFullInfo -> {}
            is GeneratorEvent.Regenerate -> {}

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
            is GeneratorEvent.Generate -> {}
            else -> {}
        }
    }

    private fun performServerQuery(amount: Int){
        viewModelScope.launch {
            val gotUsers = randomRepository.getUsers(1).toAppUser()
            if (gotUsers.isNotEmpty()){

                viewState = GeneratorState.ShowUsers(gotUsers)
            }
        }
    }
}