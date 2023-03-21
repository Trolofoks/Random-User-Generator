package com.honey.randomusergenerator.ui.screens.generator

import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEffect
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorEvent
import com.honey.randomusergenerator.ui.screens.generator.contract.GeneratorState

class GeneratorViewModel():BaseViewModel<GeneratorEvent,GeneratorState,GeneratorEffect>(initialState = GeneratorState.Empty) {
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

    }
    private fun reduce(event: GeneratorEvent, currentState: GeneratorState.Error){

    }
    private fun reduce(event: GeneratorEvent, currentState: GeneratorState.Empty){
        when(event){
            is GeneratorEvent.Generate -> {

            }
            else -> {}
        }
    }
}