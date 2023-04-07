package com.honey.randomusergenerator.ui.screens.settings

import com.honey.randomusergenerator.ui.base.BaseViewModel
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsEffect
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsEvent
import com.honey.randomusergenerator.ui.screens.settings.contracts.SettingsState

class SettingsViewModel: BaseViewModel<SettingsEvent, SettingsState, SettingsEffect>(initialState = SettingsState.Loading) {
    override fun obtainEvent(event: SettingsEvent) {
        when(val currentState = viewState){
            is SettingsState.Loading -> reduce(event, currentState)
            is SettingsState.Show -> reduce(event, currentState)
        }
    }

    private fun reduce (event: SettingsEvent, currentState: SettingsState.Loading){}
    private fun reduce (event: SettingsEvent, currentState: SettingsState.Show){
        when(event){
            is SettingsEvent.DeveloperMode -> {

            }
            is SettingsEvent.LanguageSelect -> {

            }
        }
    }



}